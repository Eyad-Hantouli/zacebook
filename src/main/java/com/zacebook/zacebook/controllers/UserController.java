package com.zacebook.zacebook.controllers;

import com.zacebook.zacebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userName}")
    public Map<String, Object> getUserById(@PathVariable String userName) {
        return userService.getUserById(userName);
    }

    @PostMapping
    public void createUser(@RequestBody Map<String, Object> requestedUser) {
        userService.createUser(requestedUser);
    }


    @PutMapping
    public void updateUser(@RequestBody Map<String, Object> requestedUser) {
        userService.updateUser(requestedUser);
    }

    @DeleteMapping(path = "/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
    }
}
