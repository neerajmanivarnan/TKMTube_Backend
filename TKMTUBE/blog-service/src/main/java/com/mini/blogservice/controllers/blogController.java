package com.mini.blogservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.blogservice.models.Blog;
import com.mini.blogservice.service.blogService;

@RestController
@RequestMapping("blogs")
public class blogController {

    @Autowired
    blogService bService;

    @GetMapping("getAllBlogs")
    public ResponseEntity<List<Blog>> getAllBlogs(){
        return bService.getBlogs();
    }

    @PostMapping("add")
    public ResponseEntity<Blog> addNewBLog(@RequestBody Blog blog){
        return bService.add(blog);
    }

}
