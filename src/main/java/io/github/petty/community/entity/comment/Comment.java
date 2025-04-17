package io.github.petty.community.entity.comment;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private String userId;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();
}
