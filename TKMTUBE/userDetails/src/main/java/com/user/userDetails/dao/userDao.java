package com.user.userDetails.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.userDetails.models.user;

@Repository
public interface userDao  extends JpaRepository<user,Integer>{

    user findByUsername(String username);

    
} 
