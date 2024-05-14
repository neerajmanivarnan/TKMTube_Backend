package com.mini.profileservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("media-service")
public interface MediaServiceFeignClient {


    @PostMapping(value="media/{username}/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Updated endpoint to include username in the URL
    public String uploadFile( MultipartFile file, @PathVariable("username") String username);

    // public String uploadImage(MultipartFile profilePicture, String username);

}
