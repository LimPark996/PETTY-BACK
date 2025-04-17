package io.github.petty.community.repository.like;

import io.github.petty.community.entity.like.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    // 1. 좋아요 눌렀는지 확인
    boolean existsByPostIdAndUserId(Long postId, String userId);

    // 2. 좋아요 수 카운트
    long countByPostId(Long postId);

    // 3. 좋아요 취소
    void deleteByPostIdAndUserId(Long postId, String userId);

    // 4. 좋아요 추가 저장 → save(PostLike like) (JpaRepository 기본 제공)
}