package com.mini.blogservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mini.blogservice.models.PostId;
import com.mini.blogservice.models.userDTO;

@FeignClient("like-service")
public interface LikeFeign {
    
    @PostMapping("likes/getLikes")
    public int getAllLikes(PostId postId);

}
