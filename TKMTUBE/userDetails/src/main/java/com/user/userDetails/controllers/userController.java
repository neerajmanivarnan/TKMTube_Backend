package com.user.userDetails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userDetails.models.user;
import com.user.userDetails.models.userDTO;
import com.user.userDetails.services.userDetailsService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins="http:localhost:3000/register")
public class userController {

    @Autowired
    userDetailsService userSservice;
    
    @PostMapping("/")
    public user addUser(@RequestBody user users){
        userSservice.postUser(users);
        return users;
    }

    @GetMapping("getUser")
    public userDTO returnUser(@RequestBody int id){
        userDTO wrappedUser = userSservice.getDTOUser(id); 
        return wrappedUser;
    }


}
