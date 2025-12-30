package com.example.userserver.rest;

import com.example.userserver.dto.JwtResponseDTO;
import com.example.userserver.dto.LoginDTO;
import com.example.userserver.dto.SignupDTO;
import com.example.userserver.dto.UserDto;
import com.example.userserver.entities.User;
import com.example.userserver.services.JwtService;
import com.example.userserver.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserService userService;

    @GetMapping("/login")
    public String test(){
        return  "okkkk";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        System.out.println(loginRequest.getEmail()+" "+loginRequest.getPassword());
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();


            String jwt = jwtService.generateToken((UserDetails) authentication.getPrincipal() , user.getId());

            return ResponseEntity.ok(new JwtResponseDTO(jwt));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password");
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDTO signupRequest) {
        User user = userService.save(signupRequest);
        UserDto userDto = new UserDto(user.getId(),user.getUsername(),user.getEmail());
        return ResponseEntity.ok(userDto);
    }


}