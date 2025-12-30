package com.example.gatewayserver.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Value("${jwt.secret}")
    private String SECRET;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // If no token → continue normally
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        try {
            String token = authHeader.substring(7);
            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
            Key key = Keys.hmacShaKeyFor(keyBytes);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            Integer userId = claims.get("userId", Integer.class);
            List<String> roles = claims.get("roles", List.class);

            // Convert roles → authorities
            var authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            // Build Spring Security Authentication object
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            // Add custom headers to forwarded request
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("x-user-id", String.valueOf(userId))
                    .header("x-roles", String.join(",", roles))
                    .build();

            ServerWebExchange mutatedExchange = exchange.mutate()
                    .request(mutatedRequest)
                    .build();

            // Continue with authentication in context
            return chain.filter(mutatedExchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authToken));

        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }

        // If token invalid → continue without authentication
        return chain.filter(exchange);
    }
}