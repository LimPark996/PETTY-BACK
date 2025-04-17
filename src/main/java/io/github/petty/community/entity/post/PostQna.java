package io.github.petty.community.entity.post;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post_qna")
@Data
public class PostQna {

    public enum PetType {
        강아지, 고양이, 햄스터, 앵무새, 거북이, 파충류, 기타
    }

    @Id
    private Long postId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    private PetType petType;
}
