package com.mini.blogservice.models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogReturn {
    public BlogReturn(BlogReturn blogToReturn) {
        this.id = blogToReturn.id;
        this.username = blogToReturn.username;
        this.title = blogToReturn.title;
        this.body = blogToReturn.body;
        this.likes = blogToReturn.likes;
        this.firstName = blogToReturn.firstName;
        this.lastName = blogToReturn.lastName;
        this.createdAt = blogToReturn.createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String title;

    @Lob
    String body;
    int likes=0;


    String firstName;
    String lastName;

    // @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
}


/*
 * username
 * title
 * body
 * likes
 * createdAt
 * 
 */