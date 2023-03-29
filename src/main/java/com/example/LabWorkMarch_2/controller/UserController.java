package com.example.LabWorkMarch_2.controller;

import com.example.LabWorkMarch_2.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public void registration(String username,String email,String password){
        userService.registration(username,email,password);
    }
}
