package main.controller;

import main.controller.response.LoginResponse;
import main.user.UserData;
import main.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserData getUserById(@PathVariable final String userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public UserData updateUser(@RequestBody final UserData userData) {
        return userService.updateUser(userData);
    }

    @CrossOrigin
    @PostMapping("/users/login/{code}")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse loginUser(@PathVariable final String code) {
        return userService.loginUser(code);
    }
}
