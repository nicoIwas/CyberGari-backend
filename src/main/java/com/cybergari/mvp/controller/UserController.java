package com.cybergari.mvp.controller;

import com.cybergari.mvp.controller.response.LoginResponse;
import com.cybergari.mvp.user.UserData;
import com.cybergari.mvp.user.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserData getUserById(@PathVariable final String userId) {
        log.info("Getting user: {}", userId);
        return userService.findUserById(userId);
    }


    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public UserData updateUser(@RequestBody final UserData userData) {
        log.info("Updating data to user: {}", userData);
        return userService.updateUser(userData);
    }

    @CrossOrigin
    @PostMapping("/users/login/{code}")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse loginUser(@PathVariable final String code) {
        return userService.loginUser(code);
    }
}
