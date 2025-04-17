package io.github.petty.community.entity.post;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post_review")
@Data
public class PostReview {

    @Id
    private Long postId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "post_id")
    private Post post;

    private String petName;
    private String petType;
    private String region;
}
