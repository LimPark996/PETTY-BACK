package io.github.petty.community.entity.post;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post_qna")
@Data
public class PostQna {

    @Id
    private Long postId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "post_id")
    private Post post;

    private String petType;
    private boolean isResolved;
}
