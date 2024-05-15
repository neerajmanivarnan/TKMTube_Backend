package com.mediaservice.mediaservice;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@CrossOrigin
public class FileUploadController {

    @Autowired
    private Storage storage;

    @Autowired
    private MediaRepository mediaRepository; // Assuming you have a MediaRepository interface

    //initial url = @PostMapping("/{username}/upload")
    @PostMapping(value="media/{username}/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Updated endpoint to include username in the URL
    public ResponseEntity<String> uploadFile( MultipartFile file, @PathVariable("username") String username) throws IOException {
        // Upload file to Google Cloud Storage bucket
        BlobInfo blobInfo = BlobInfo.newBuilder("prathyush-bucket", file.getOriginalFilename()).build();
        Blob blob = storage.create(blobInfo, file.getBytes());
        String mediaUrl = blob.getMediaLink();
        
        // Insert data into MySQL database
        mediaRepository.save(new Media(username, mediaUrl, new Timestamp(new Date().getTime())));

        // Return URL of the uploaded file
        return ResponseEntity.ok().body(mediaUrl);
    }
}



// public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("username") String username) throws IOException {
//     // Upload file to Google Cloud Storage bucket
//     BlobInfo blobInfo = BlobInfo.newBuilder("prathyush-bucket", file.getOriginalFilename()).build();
//     Blob blob = storage.create(blobInfo, file.getBytes());
//     String mediaUrl = blob.getMediaLink();
    
//     // Insert data into MySQL database
//     mediaRepository.save(new Media(username, mediaUrl, new Timestamp(new Date().getTime())));

//     // Return URL of the uploaded file
//     return ResponseEntity.ok().body(mediaUrl);
// }











































// package com.mediaservice.mediaservice;

// import com.google.cloud.storage.Blob;
// import com.google.cloud.storage.BlobInfo;
// import com.google.cloud.storage.Storage;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.sql.Timestamp;
// import java.util.Date;

// @RestController
// public class FileUploadController {
//     @Autowired
//     private Storage storage;

//     @Autowired
//     private MediaRepository mediaRepository;

//     @PostMapping("/upload")
//     public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//         // Upload file to Google Cloud Storage bucket
//         BlobInfo blobInfo = BlobInfo.newBuilder("prathyush-bucket", file.getOriginalFilename()).build();
//         Blob blob = storage.create(blobInfo, file.getBytes());
//         String mediaUrl = blob.getMediaLink();

//         mediaRepository.save(new Media(username, mediaUrl, new Timestamp(new Date().getTime())));
//         // Return URL of the uploaded file
//         return ResponseEntity.ok().body(blob.getMediaLink());
//     }
// }
