package com.mini.profileservice.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.profileservice.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    Profile findByUsername(String username);

    
} 
