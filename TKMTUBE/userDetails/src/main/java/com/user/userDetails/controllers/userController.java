package com.user.userDetails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userDetails.models.user;
import com.user.userDetails.services.userDetailsService;

@RestController
@RequestMapping("users")
public class userController {

    @Autowired
    userDetailsService userSservice;
    
    @PostMapping("/")
    public user addUser(@RequestBody user users){
        userSservice.postUser(users);
        return users;
    }


}
