package com.identityservice.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.identityservice.authentication.models.user;


@Repository
public interface userRepo extends JpaRepository<user,Integer>{

    user findByUsername(String username);
    
}
