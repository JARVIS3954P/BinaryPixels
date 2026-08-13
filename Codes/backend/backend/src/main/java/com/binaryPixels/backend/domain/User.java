package com.binaryPixels.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name="display_name", nullable = false, length = 100)
    private String displayName;

    @Column(name="created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void onCreate(){
        if(createdAt == null){
            createdAt = Instant.now();
        }
    }
}
