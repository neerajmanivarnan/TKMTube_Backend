package com.mini.galleryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GalleryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalleryServiceApplication.class, args);
	}

}
