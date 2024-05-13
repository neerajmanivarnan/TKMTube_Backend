// package com.mediaservice.mediaservice;

// import com.google.cloud.storage.BlobId;
// import com.google.cloud.storage.Blob;
// import com.google.cloud.storage.Storage;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.InputStreamResource;
// import org.springframework.core.io.Resource;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;

// import java.io.ByteArrayInputStream;
// import java.io.InputStream;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// @RestController
// public class FileFetchController {

//     @Autowired
//     private Storage storage; // Assuming you have configured Google Cloud Storage beans properly

//     @Autowired
//     private MediaRepository mediaRepository; // Assuming you have a MediaRepository interface

//     @GetMapping("/{username}/fetch") // Endpoint to fetch images for a specific username
//     public ResponseEntity<List<Resource>> fetchImages(@PathVariable("username") String username) {
//         // Retrieve images based on the username from the database
//         List<Media> mediaList = mediaRepository.findByUsername(username);

//         // If no images found for the given username, return a not found response
//         if (mediaList.isEmpty()) {
//             return ResponseEntity.notFound().build();
//         }

//         // Fetch images from Google Cloud Storage using the URLs stored in the database
//         List<Resource> imageResources = mediaList.stream()
//                 .map(media -> fetchImageResource(media.getMediaUrl()))
//                 .filter(Optional::isPresent)
//                 .map(Optional::get)
//                 .collect(Collectors.toList());

//         // Return the fetched images as resources
//         return ResponseEntity.ok().body(imageResources);
//     }

//     private Optional<Resource> fetchImageResource(String imageUrl) {
//         try {
//             // Extract object name from the image URL
//             String objectName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            
//             // Fetch image from Google Cloud Storage using the object name
//             Blob blob = storage.get(BlobId.of("prathyush-bucket", objectName));
            
//             if (blob != null) {
//                 // Create an InputStream from the blob content
//                 InputStream inputStream = new ByteArrayInputStream(blob.getContent());
                
//                 // Return the image as a resource
//                 return Optional.of(new InputStreamResource(inputStream));
//             } else {
//                 return Optional.empty();
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             return Optional.empty();
//         }
//     }
// }













































package com.mediaservice.mediaservice;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileFetchController {

    @Autowired
    private MediaRepository mediaRepository; // Assuming you have a MediaRepository interface

    @GetMapping("/{username}/fetch") // Endpoint to fetch images for a specific username
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