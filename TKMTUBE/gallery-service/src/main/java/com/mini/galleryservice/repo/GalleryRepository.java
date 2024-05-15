package com.mini.galleryservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.galleryservice.models.Profile;


@Repository
public interface GalleryRepository extends JpaRepository<Profile,Integer> {

    Profile findByUsername(String username);

}
