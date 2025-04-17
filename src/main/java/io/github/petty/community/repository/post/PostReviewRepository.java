package io.github.petty.community.repository.post;

import io.github.petty.community.entity.post.PostReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReviewRepository extends JpaRepository<PostReview, Long> {

    // 1. 리뷰 상세 정보 조회
    PostReview findByPostId(Long postId);

    // 2. 리뷰 저장 → save(PostReview review) (JpaRepository 기본 제공)
}
