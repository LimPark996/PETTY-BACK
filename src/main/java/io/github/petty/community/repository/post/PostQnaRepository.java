package io.github.petty.community.repository.post;

import io.github.petty.community.entity.post.PostQna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostQnaRepository extends JpaRepository<PostQna, Long> {

    // 1. QnA 상세 정보 불러오기
    PostQna findByPostId(Long postId);

    // 2. QnA 추가 저장 → save(postQna) ← JpaRepository 기본 메서드 (생략 가능)
}