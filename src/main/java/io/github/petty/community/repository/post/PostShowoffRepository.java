package io.github.petty.community.repository.post;

import io.github.petty.community.entity.post.PostShowoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostShowoffRepository extends JpaRepository<PostShowoff, Long> {

    // 1. 자랑글 상세 조회
    Optional<PostShowoff> findByPostId(Long postId);

    // 2. 자랑글 저장 → save(PostShowoff showoff) (기본 제공, 생략 가능)
}