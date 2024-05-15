package com.mini.blogservice.controllers;


import java.util.List;


// import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchangeDecorator;

import com.mini.blogservice.models.Blog;
import com.mini.blogservice.models.BlogReturn;
import com.mini.blogservice.service.JwtService;
import com.mini.blogservice.service.blogService;

import jakarta.ws.rs.core.SecurityContext;

@RestController
@RequestMapping("blogs")
// @CrossOrigin(origins = "http://localhost:3000/blog")
@CrossOrigin
public class blogController {

    @Autowired
    blogService bService;

    @Autowired
    JwtService jService;

    // @Autowired
    // Authentication authentication;
    // Authentication authentication;
    Authentication authentication;


    @GetMapping("/")
    public ResponseEntity<List<BlogReturn>> getAllBlogs(){
        return bService.getBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable("id") int id ){
        return bService.getBlog(id);
    }

    @PostMapping("add")
    public ResponseEntity<Blog> addNewBLog(@RequestHeader("LoggedInUser") String username, @RequestBody Blog blog){
        blog.setUsername(username);
        return bService.add(blog);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id){
        return bService.remove(id);
    }

    @PutMapping("likes/{id}")
    public ResponseEntity<String> addLikes(@PathVariable("id") int id){
        return bService.addLike(id);
    }

    @GetMapping("test")
    public String getMessage(@RequestHeader("LoggedInUser") String username,@RequestBody String content){

            System.out.println(username);
            return username;
              
    }

}
