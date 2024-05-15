package com.mini.galleryservice.services;

import com.mini.galleryservice.feign.MediaServiceFeignClient;
import com.mini.galleryservice.models.Profile;
import com.mini.galleryservice.repo.GalleryRepository;


// Assuming Feign interface for media service
// import com.profileservice.model.Profile;
// import com.profileservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository profileRepository;

    @Autowired
    private MediaServiceFeignClient mediaServiceFeignClient;

    public Profile createProfile( String username, MultipartFile profilePicture) throws IOException {

        
        String imageUrl = null;
        if (profilePicture != null) {
            imageUrl = mediaServiceFeignClient.uploadFile(profilePicture,username); // Call media service to upload image
        }

        Profile profile = new Profile();
        profile.setUsername(username);
        // profile.setFirstName(firstName);
        // profile.setLastName(lastName);
        profile.setProfilePictureUrl(imageUrl);

        return profileRepository.save(profile);
    }

    public String fetchUrlFromDb(String username) {
        Profile profile = profileRepository.findByUsername(username);
        return profile.getProfilePictureUrl();
    }

    public List<Profile> getAll() {
       List<Profile> profiles = profileRepository.findAll();
       return profiles;
    }

    // Implement other service methods like getProfile, updateProfile, etc. (omitted for brevity)
}
