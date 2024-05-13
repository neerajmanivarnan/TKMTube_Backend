package com.identityservice.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.identityservice.authentication.models.user;
import com.identityservice.authentication.repo.userRepo;



@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private userRepo repo;
    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user credential = repo.findByUsername(username);

        if(credential!= null){
            return new CustomUserDetails(credential);
        }else{
            throw new UsernameNotFoundException("username not found");
        }

        // return ((Object) credential).map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("username not found"));
    }

}
