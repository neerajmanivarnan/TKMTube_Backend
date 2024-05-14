package com.mini.likeservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDao extends JpaRepository<Likes,Integer> {


    @Query("SELECT p FROM Likes p where p.postId= ?1 and p.username = ?2")
    Likes findAllPostIdAndUsername(int postId,String username);

    Optional<List<Likes>> findByPostId(int postIdentifier);

}
