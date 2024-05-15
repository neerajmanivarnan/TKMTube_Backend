package com.mediaservice.mediaservice;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FileFetchController {

    @Autowired
    private MediaRepository mediaRepository; // Assuming you have a MediaRepository interface

    // initial url = @GetMapping("/{username}/fetch")
    @GetMapping("media/{username}/fetch") // Endpoint to fetch images for a specific username
    public ResponseEntity<List<String>> fetchImages(@PathVariable("username") String username) {
        // Retrieve images based on the username from the database
        List<Media> mediaList = mediaRepository.findByUsername(username);

        // If no images found for the given username, return a not found response
        if (mediaList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Assuming you have stored the URLs of the images in the 'mediaUrl' field of the Media entity
        List<String> imageUrls = mediaList.stream()
                                         .map(Media::getMediaUrl) // Use getMediaUrl method to get the image URL
                                         .collect(Collectors.toList());

        // Return the fetched image URLs
        return ResponseEntity.ok().body(imageUrls);
    }
}