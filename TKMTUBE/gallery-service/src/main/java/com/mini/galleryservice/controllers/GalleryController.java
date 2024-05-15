package com.mini.galleryservice.controllers;


import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mini.galleryservice.models.Profile;
import com.mini.galleryservice.models.UserDTOBody;
import com.mini.galleryservice.services.GalleryService;



@RestController
@RequestMapping("gallery")
@CrossOrigin
public class GalleryController {
    
    @Autowired
    GalleryService pService;

    @PostMapping("/")
    public List<Profile> getProfilePictureUrl(@RequestBody UserDTOBody username){
        // String url =  pService.fetchUrlFromDb(username.getUsername());
        return pService.getAll();
        
    }
    
    
    @PostMapping(value="upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Updated endpoint to include username in the URL
    public String uploadFile( MultipartFile file, @RequestHeader("LoggedInUser") String username) throws IOException {

        pService.createProfile(username, file);
        return "Success";

        // // Upload file to Google Cloud Storage bucket
        // BlobInfo blobInfo = BlobInfo.newBuilder("prathyush-bucket", file.getOriginalFilename()).build();
        // Blob blob = storage.create(blobInfo, file.getBytes());
        // String mediaUrl = blob.getMediaLink();
        
        // // Insert data into MySQL database
        // mediaRepository.save(new Media(username, mediaUrl, new Timestamp(new Date().getTime())));

        // // Return URL of the uploaded file
        // return ResponseEntity.ok().body(mediaUrl);
    } 

}
