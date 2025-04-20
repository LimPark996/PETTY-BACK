package io.github.petty.community.service;

import io.github.petty.community.dto.PostImageRequest;
import io.github.petty.community.entity.Post;
import io.github.petty.community.entity.PostImage;
import io.github.petty.community.repository.PostImageRepository;
import io.github.petty.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostImageServiceImpl implements PostImageService {

    private final PostImageRepository postImageRepository;
    private final PostRepository postRepository;

    @Override
    public void saveImages(Post post, List<String> imageUrls) {
        if (imageUrls == null || imageUrls.isEmpty()) return;

        int order = 0;
        for (String url : imageUrls) {
            PostImage image = new PostImage();
            image.setImageUrl(url);
            image.setOrdering(order++);
            image.setPost(post);
            postImageRepository.save(image);
        }
    }

    @Override
    public void deleteImagesByPostId(Long postId) {
        postImageRepository.deleteByPostId(postId);
    }

    @Override
    public void deleteImage(Long imageId) {
        postImageRepository.deleteById(imageId);
    }

    @Override
    public List<String> findImageUrlsByPostId(Long postId) {
        return postImageRepository.findByPostIdOrderByOrderingAsc(postId).stream()
                .map(PostImage::getImageUrl)
                .toList();
    }


    @Override
    @Transactional
    public void reorderImages(List<Long> orderedImageIds) {
        for (int i = 0; i < orderedImageIds.size(); i++) {
            Long imageId = orderedImageIds.get(i);
            PostImage image = postImageRepository.findById(imageId)
                    .orElseThrow(() -> new IllegalArgumentException("이미지 ID가 잘못되었습니다."));
            image.setOrdering(i);  // 새로운 순서로 업데이트
        }
    }

    @Override
    @Transactional
    public void updateImagesFromRequest(Post post, List<PostImageRequest> imageRequests) {
        for (PostImageRequest dto : imageRequests) {
            if (dto.getIsDeleted() != null && dto.getIsDeleted()) {
                // 삭제 요청
                if (dto.getId() != null) postImageRepository.deleteById(dto.getId());
            } else if (dto.getId() != null) {
                // 기존 이미지 수정
                PostImage image = postImageRepository.findById(dto.getId())
                        .orElseThrow(() -> new IllegalArgumentException("이미지를 찾을 수 없습니다."));
                image.setImageUrl(dto.getImageUrl());
                image.setOrdering(dto.getOrdering());
            } else {
                // 신규 이미지 추가
                PostImage newImage = new PostImage();
                newImage.setImageUrl(dto.getImageUrl());
                newImage.setOrdering(dto.getOrdering());
                newImage.setPost(post);
                postImageRepository.save(newImage);
            }
        }
    }
}

