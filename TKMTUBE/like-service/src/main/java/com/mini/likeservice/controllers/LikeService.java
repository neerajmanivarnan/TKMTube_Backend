package com.mini.likeservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.likeservice.models.PostId;

@Service
public class LikeService {

    @Autowired
    LikeDao likeDao;


    public int addLikes(String username, PostId postId) {

        int postIdentifier = postId.getPostId();
        System.out.println(postIdentifier);
        System.out.println(username);
        int numberOfLikes;

        Likes likes = null;
        

         likes = likeDao.findAllPostIdAndUsername(postIdentifier, username);
        int likesToBeUpdated;

        if(likes!=null){
            if(likes.likeCount == 0){
                numberOfLikes = likes.likeCount;
                numberOfLikes= numberOfLikes+1;
                likesToBeUpdated = numberOfLikes;
                likes.setPostId(postIdentifier);
                likes.setUsername(username);
                likes.setLikeCount(numberOfLikes);
                likeDao.save(likes);
            }else if(likes.likeCount == 1){
                numberOfLikes = 0;
                likesToBeUpdated=-1;
                likes.setPostId(postIdentifier);
                likes.setUsername(username);
                likes.setLikeCount(numberOfLikes);
                likeDao.save(likes);
            }
        }else{
            Likes likeSaver = new Likes() ;
            likeSaver.setLikeCount(1);
            likeSaver.setPostId(postIdentifier);
            likesToBeUpdated = 1;
            likeSaver.setUsername(username);
            likeDao.save(likeSaver);
        }

        Optional<List<Likes>>  likeList=  likeDao.findByPostId(postIdentifier);

        List<Likes> OptionalReturnList = likeList.get();

        int sum = 0;

        

        for(Likes n : OptionalReturnList){
            sum = sum + n.likeCount;
        }


        return sum;

        
    }

}
