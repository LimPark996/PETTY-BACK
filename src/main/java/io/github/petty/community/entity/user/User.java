package io.github.petty.community.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;
    private String role;
    private String displayName;

    private LocalDateTime createdAt = LocalDateTime.now();
}
