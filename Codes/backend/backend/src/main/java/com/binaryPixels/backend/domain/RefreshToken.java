package com.binaryPixels.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user.id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @Column(name="expires_at", nullable = false, updatable = false)
    private Instant expiresAt;

    @PrePersist
    void onCreate(){
        if(createdAt == null){
            createdAt = Instant.now();
        }
    }
}
