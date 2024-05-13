package com.mini.blogservice.controllers;


import java.util.List;
// import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mini.blogservice.models.Blog;
import com.mini.blogservice.models.BlogReturn;
import com.mini.blogservice.service.blogService;

@RestController
@RequestMapping("blogs")
@CrossOrigin(origins = "http://localhost:3000/blog")
public class blogController {

    @Autowired
    blogService bService;

    @GetMapping("/")
    public ResponseEntity<List<BlogReturn>> getAllBlogs(){
        return bService.getBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable("id") int id ){
        return bService.getBlog(id);
    }

    @PostMapping("add")
    public ResponseEntity<Blog> addNewBLog(@RequestBody Blog blog){
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

}
