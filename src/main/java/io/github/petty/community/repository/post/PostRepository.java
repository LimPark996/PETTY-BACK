package io.github.petty.community.repository.post;

import io.github.petty.community.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 1. 글 목록 조회
    Page<Post> findAllByPostType(String type, Pageable pageable);

    // 2. 글 상세 조회
    Optional<Post> findById(Long id); // JpaRepository에 기본 포함되어 있지만 명시 가능

    // 3. 게시글 등록 → save(Post post) (생략 가능, JpaRepository 기본 메서드)

    // 4. 게시글 삭제 → deleteById(Long id) (생략 가능, JpaRepository 기본 메서드)

    // 5. 글 작성자인지 확인 (user는 연관관계로 연결되어 있다고 가정)
    boolean existsByIdAndUser_Id(Long postId, UUID userId);
}
