package com.user.userDetails.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.userDetails.dao.userDao;
import com.user.userDetails.models.user;

@Service
public class userDetailsService {

    @Autowired
    userDao dao;
    public void postUser(user users){

        dao.save(users);
    }


}
