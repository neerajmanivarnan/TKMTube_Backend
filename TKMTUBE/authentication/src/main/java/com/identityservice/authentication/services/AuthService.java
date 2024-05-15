package com.identityservice.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.identityservice.authentication.models.user;
import com.identityservice.authentication.models.userDTO;
import com.identityservice.authentication.repo.userRepo;
import com.netflix.discovery.converters.Auto;


@Service
public class AuthService {
    
    @Autowired
    userRepo repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    

    public String saveUser(user credential){

        System.out.println("save user reached");

        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repo.save(credential);
        return "user added ro server";
    }

    public String generateToken(String username){

        
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }


    public userDTO getDTOUser(String  username) {
        try{
            user orgUser =  repo.findByUsername(username);
        System.out.println(orgUser.getUsername());
        String First = orgUser.getFirstName();
        String Last = orgUser.getLastName();

        userDTO wrappedUser=new userDTO(First, Last); 
        System.out.println(First);
        System.out.println(Last);

        return wrappedUser;
        }catch(Exception e){
            System.out.println("error occured");
            return null;
        }
    }



    
}
