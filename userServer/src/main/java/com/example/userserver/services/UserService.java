package com.example.userserver.services;

import com.example.userserver.dto.SignupDTO;
import com.example.userserver.entities.User;

import java.util.Optional;

public interface UserService {

    User getCurrentUser();
    User save(SignupDTO signupDTO);
    String getUserRole(int user_id);
    Optional<User> getUserById(int userId);

}
