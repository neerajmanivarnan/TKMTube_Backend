package com.identityservice.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.identityservice.authentication.dto.AuthDTO;
import com.identityservice.authentication.models.user;
import com.identityservice.authentication.models.userDTO;
import com.identityservice.authentication.services.AuthService;
import com.identityservice.authentication.services.JwtService;
import com.netflix.discovery.converters.Auto;

@RestController
@CrossOrigin
public class AuthController {
    
    @Autowired
    AuthService authService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("auth/register")
    public String addUser(@RequestBody user users){

        System.out.println("controller reached");
        return authService.saveUser(users);
        
    }

    @PostMapping("auth/token")
    public String getToken(@RequestBody AuthDTO authDto){

        Authentication authenticate = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        
        if(authenticate.isAuthenticated()){
                return jwtService.generateToken(authDto.getUsername());
        }else{
            // return "Not a valid token";
            throw new RuntimeException("invalid user");
        }

    }

    @GetMapping("auth/validate")
    public String validateToken(@RequestParam("token") String token){
         jwtService.validateToken(token);
         return "Token validated";
    }

    @GetMapping("auth/getUser/{username}")
    public userDTO returnUser(@PathVariable("username") String username){
        userDTO wrappedUser = authService.getDTOUser(username); 
        return wrappedUser;
    }

    @GetMapping("auth/extractUsername")
    public String getUsernameMap(@RequestParam("token") String token){
        String username = jwtService.extractUsername(token);
        return username;
    }


}
