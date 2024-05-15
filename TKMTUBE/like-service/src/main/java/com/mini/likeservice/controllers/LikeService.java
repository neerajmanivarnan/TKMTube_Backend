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


    public int countAllLikesOfAPostByUsername(PostId postId) {
        System.out.println("called servicec method from like service");
        Optional<List<Likes>> likeList = likeDao.findByPostId(postId.getPostId());
        List<Likes> likesToBeReturned = likeList.get();

        int LikeCountOfAPost=0;
        for(Likes n: likesToBeReturned){
            LikeCountOfAPost = LikeCountOfAPost+ n.likeCount;
        }

        System.out.println(postId.getPostId()+" : "+LikeCountOfAPost);

        return LikeCountOfAPost;

    }


  

}
