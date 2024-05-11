package com.user.userDetails.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.user.userDetails.dao.userDao;
import com.user.userDetails.models.user;
import com.user.userDetails.models.userDTO;

@Service
public class userDetailsService {

    @Autowired
    userDao dao;
    public void postUser(user users){

        dao.save(users);
    }
    public userDTO getDTOUser(int id) {
        try{
            Optional<user> orgUser =  dao.findById(id);
        System.out.println(orgUser.get().getUsername());
        String First = orgUser.get().getFirstName();
        String Last = orgUser.get().getLastName();

        userDTO wrappedUser=new userDTO(First, Last); 

        return wrappedUser;
        }catch(Exception e){
            System.out.println("error occured");
            return null;
        }
    }


}
