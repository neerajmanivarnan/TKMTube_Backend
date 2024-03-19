package com.mini.blogservice.models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
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
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String title;
    String body;


    // @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
}
