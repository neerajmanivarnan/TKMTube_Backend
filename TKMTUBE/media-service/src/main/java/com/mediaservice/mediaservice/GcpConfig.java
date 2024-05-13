package com.mediaservice.mediaservice;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GcpConfig {
   
    @Bean
    public Storage storage() {
        try {
            // Load the service account credentials file from the classpath
            InputStream inputStream = new ClassPathResource("media-service-423007-6e77fb38f801.json").getInputStream();
            
            // Build the Storage client with the loaded credentials
            return StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(inputStream))
                    .build()
                    .getService();
        } catch (IOException e) {
            // Handle the IOException appropriately
            e.printStackTrace();
            return null; // Or throw a custom exception
        }
    }
}