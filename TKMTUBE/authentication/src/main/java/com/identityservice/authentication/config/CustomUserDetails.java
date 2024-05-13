package com.identityservice.authentication.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.identityservice.authentication.models.user;

import io.jsonwebtoken.lang.Collections;

public class CustomUserDetails  implements UserDetails{

    private String username;
    private String password;

    public CustomUserDetails(user users){
        this.username = users.getUsername();
        this.password = users.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            // List<GrantedAuthority> authorities = new ArrayList<>();
            // if (// Check if user has "USER" role) {
            //     authorities.add(new SimpleGrantedAuthority("USER"));
            // }
            // // if (// Check if user has "ADMIN" role) {
            // //     authorities.add(new SimpleGrantedAuthority("ADMIN"));
            // // }
            // // return authorities;
            // return Collections.singleton(new SimpleGrantedAuthority("USER"));

            return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }

    @Override
    public boolean isEnabled() {
        
       return true;
    }
    
}
