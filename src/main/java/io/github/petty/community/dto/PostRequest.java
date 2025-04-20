package io.github.petty.community.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostRequest {
    private String title;
    private String content;
    private String postType;   // REVIEW, QNA, SHOWOFF
    private String petName;
    private String petType;
    private String region;
    private Boolean isResolved;
    private List<String> imageUrls;

    private List<PostImageRequest> images;
}
