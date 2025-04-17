package io.github.petty.community.repository.image;

import io.github.petty.community.entity.image.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    // 1. 해당 게시글의 이미지 목록 조회 (순서대로)
    List<PostImage> findByPostIdOrderByOrderingAsc(Long postId);

    // 2. 여러 이미지 한 번에 저장 → saveAll(List<PostImage> images) (JpaRepository 기본 제공)

    // 3. 게시글 삭제 시 이미지도 함께 삭제
    @Transactional
    void deleteByPostId(Long postId);
}