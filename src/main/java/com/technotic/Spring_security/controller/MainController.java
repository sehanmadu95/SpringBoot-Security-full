package com.technotic.Spring_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public  String getHome(HttpServletRequest request){
        return "Hello Welcome to the Spring Security development Project...."+request.getSession().getId();
    }
}
