package io.github.petty.community.service;

import io.github.petty.community.dto.PostImageRequest;
import io.github.petty.community.entity.Post;

import java.util.List;

public interface PostImageService {

    // 이미지 URL 리스트를 받아 PostImage로 저장
    void saveImages(Post post, List<String> imageUrls);

    // 게시글 삭제 전 이미지 모두 제거 (PostService에서 위임)
    void deleteImagesByPostId(Long postId);

    // 향후 이미지 수정 단건 처리 등을 위해 준비
    void deleteImage(Long imageId);

    // 이미지 순서 변경
    void reorderImages(List<Long> orderedImageIds);

    // PostImageRequest 기반으로 수정/삭제/추가 처리
    void updateImagesFromRequest(Post post, List<PostImageRequest> imageRequests);

    // 필요한 경우를 대비한 조회 메서드
    List<String> findImageUrlsByPostId(Long postId);
}
