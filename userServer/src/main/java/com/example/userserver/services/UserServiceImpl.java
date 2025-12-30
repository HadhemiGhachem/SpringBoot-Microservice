package com.example.userserver.services;

import com.example.userserver.dto.SignupDTO;
import com.example.userserver.entities.User;
import com.example.userserver.exception.EmailAlreadyExistsException;
import com.example.userserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        return userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User save(SignupDTO signupRequest) {
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already in use!");
        }
        User user = new User();
        user.setUserName(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole());

        return  userRepository.save(user);


    }

    @Override
    public String getUserRole(int user_id) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getAuthorities().toString();

    }

    @Override
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }
}