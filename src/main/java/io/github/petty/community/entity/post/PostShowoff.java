package io.github.petty.community.entity.post;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post_showoff")
@Data
public class PostShowoff {

    @Id
    private Long postId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "post_id")
    private Post post;

    private String petType;
}
