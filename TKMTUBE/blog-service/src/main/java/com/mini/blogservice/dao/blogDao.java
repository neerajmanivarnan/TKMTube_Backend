package com.mini.blogservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.blogservice.models.Blog;

@Repository
public interface blogDao extends JpaRepository<Blog,Integer>{
    
}
