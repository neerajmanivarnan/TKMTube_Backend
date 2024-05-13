package com.identityservice.authentication.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    int id;
    String username;
    String password;
    String email;
    String firstName;
    String lastName;


}
