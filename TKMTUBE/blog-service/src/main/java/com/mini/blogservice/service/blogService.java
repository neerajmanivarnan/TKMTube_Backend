package com.mini.blogservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mini.blogservice.dao.blogDao;
import com.mini.blogservice.models.Blog;

@Service
public class blogService {

    @Autowired
    blogDao bDao;


    public ResponseEntity<List<Blog>> getBlogs() {
        return new ResponseEntity<>(bDao.findAll(),HttpStatus.OK);
    }


    public ResponseEntity<Blog> add(Blog blog) {
       bDao.save(blog);
       return new ResponseEntity<>(blog,HttpStatus.CREATED);
    }


   
    public ResponseEntity<Optional<Blog>> getBlog(int id) {
        Optional<Blog> blog = bDao.findById(id);

        return  new ResponseEntity<>(blog,HttpStatus.OK);
    }
    
}
