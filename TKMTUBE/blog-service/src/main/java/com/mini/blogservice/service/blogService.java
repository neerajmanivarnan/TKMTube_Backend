package com.mini.blogservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mini.blogservice.dao.blogDao;
import com.mini.blogservice.feign.feign;
import com.mini.blogservice.models.Blog;
import com.mini.blogservice.models.BlogReturn;
import com.mini.blogservice.models.userDTO;

@Service
public class blogService {

    @Autowired
    blogDao bDao;

    @Autowired
    feign feignclient;

    public ResponseEntity<List<Blog>> getBlogsFromBlogService() {
        
        
        List<Blog> blogs = bDao.findAll(); 
        



        

        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }


    // public ResponseEntity<List<BlogReturn>> getBlogs() {
        

    //     List<Blog> blogs = bDao.findAll(); 
    //     // List of blogs from the database

    //     userDTO wrap1 = new userDTO(null, null);
    //     //wrap element to be added 

    //     List<BlogReturn> blogsToBeReturned = new  ArrayList<>();
    //     //declared the list to be returned after wrapping

    //     BlogReturn blogToBeReturned = new BlogReturn();

    //     for(int i=0;i<blogs.size();i++){
            
    //         blogToBeReturned.setUsername(blogs.get(i).getUsername());
    //         blogToBeReturned.setId(blogs.get(i).getId()) ;    
    //         wrap1 = feignclient.returnUser(blogToBeReturned.getUsername());
    //         blogToBeReturned.setFirstName(wrap1.getFirstName());
    //         blogToBeReturned.setLastName(wrap1.getLastName());
    //         blogToBeReturned.setTitle(blogs.get(i).getTitle());
    //         blogToBeReturned.setBody(blogs.get(i).getBody());
    //         blogToBeReturned.setLikes(blogs.get(i).getLikes());
    //         blogToBeReturned.setCreatedAt(blogs.get(i).getCreatedAt());
    //         blogsToBeReturned.add(blogToBeReturned);
            
    //     }

    //     return new ResponseEntity<>(blogsToBeReturned,HttpStatus.OK);
    // }


    public ResponseEntity<List<BlogReturn>> getBlogs() {

        System.out.println("reached blog service");

        List<Blog> blogs = bDao.findAll();  // List of blogs from the database
        List<BlogReturn> blogsToBeReturned = new ArrayList<>();  // List to store enriched blogs
    
        // Create objects to be reused within the loop
        BlogReturn blogToReturn = new BlogReturn();
        userDTO user = new userDTO(); // Assuming userDTO has firstName and lastName
    
        for (Blog blog : blogs) {
            blogToReturn.setUsername(blog.getUsername());
            blogToReturn.setId(blog.getId());
            blogToReturn.setTitle(blog.getTitle());
            blogToReturn.setBody(blog.getBody());
            blogToReturn.setLikes(blog.getLikes());
            blogToReturn.setCreatedAt(blog.getCreatedAt());
    
            // Fetch user data using Feign (handle potential exceptions)
            try {
                System.out.println("entered the try block");
                user = feignclient.returnUser(blog.getUsername());
                System.out.println("called feign client");
                if (user != null) { // Check for null before accessing fields
                    System.out.println("user is not null");
                    blogToReturn.setFirstName(user.getFirstName());
                    blogToReturn.setLastName(user.getLastName());
                } else {
                    // Handle case where user data is not found (e.g., set default values)
                    // You might log a warning or set placeholder values for first and last name
                }
            } catch (Exception e) {
                // Handle Feign client exceptions appropriately (e.g., log the error)
                // Consider returning an error response
            }
    
            blogsToBeReturned.add(new BlogReturn(blogToReturn)); // Create a copy to avoid modifying the reused object
            // Consider using a copy constructor in BlogReturn if available
        }
    
        return new ResponseEntity<>(blogsToBeReturned, HttpStatus.OK);
    }
    


    public ResponseEntity<Blog> add(Blog blog) {
       bDao.save(blog);
       return new ResponseEntity<>(blog,HttpStatus.CREATED);
    }


   
    public ResponseEntity<Blog> getBlog(int id) {
        Blog blog = bDao.findById(id);

        return  new ResponseEntity<>(blog,HttpStatus.OK);
    }


    public ResponseEntity<Blog> remove(int id) {
        // Optional<Blog> blog = bDao.findById(id);
        Blog blog = bDao.findById(id);
        bDao.deleteById(id);

        return new ResponseEntity<>(blog,HttpStatus.OK);

    }


   
    
    public ResponseEntity<String> addLike(int id) {
        Blog blog = bDao.findById(id);

        

            try{
                int likeCount = blog.getLikes();
                // System.out.println(likeCount);
                likeCount++;

                blog.setLikes(likeCount);
                bDao.save(blog);
            }catch(Exception e){
                System.out.println(e);
            }

        
        
        

        return new ResponseEntity<>("Success",HttpStatus.OK);
        
    }
    
}
