package com.mini.likeservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.likeservice.models.PostId;

@RestController
@RequestMapping("likes")
@CrossOrigin
public class likeController {

    @Autowired
    LikeService likeService;

    @PutMapping("/")
    public int addLikes(@RequestHeader("LoggedInUser") String username,@RequestBody  PostId postId){
        return likeService.addLikes(username,postId);
    }

    @PostMapping("getLikes")
    public int getAllLikes(@RequestBody PostId  postId){
        System.out.println("called like controller");
        return likeService.countAllLikesOfAPostByUsername(postId);
    }


}
