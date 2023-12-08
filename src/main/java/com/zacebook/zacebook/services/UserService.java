package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> getUserById(String userName) {
        return new HashMap<>();
    }

    public void createUser(Map<String, Object> requestedUser) {

    }

    public void updateUser(Map<String, Object> requestedUser) {

    }

    public void deleteUser(String userName) {

    }
}
