package com.technotic.Spring_security.controller;

import com.technotic.Spring_security.model.Users;
import com.technotic.Spring_security.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save-user")
    public Users createUser(@RequestBody Users users){
      return  userService.createUser(users);
    }
}
