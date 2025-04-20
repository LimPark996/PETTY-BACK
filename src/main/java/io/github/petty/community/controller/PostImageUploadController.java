package io.github.petty.community.controller;

import io.github.petty.community.util.SupabaseUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class PostImageUploadController {

    private final SupabaseUploader supabaseUploader;

    // ✅ 단일 이미지 업로드
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = supabaseUploader.upload(file);
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "업로드 실패", "message", e.getMessage()));
        }
    }

    // ✅ 다중 이미지 업로드
    @PostMapping("/upload/multi")
    public ResponseEntity<?> uploadImages(@RequestParam("files") List<MultipartFile> files) {
        List<String> imageUrls = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                String url = supabaseUploader.upload(file);
                imageUrls.add(url);
            }
            return ResponseEntity.ok(Map.of("urls", imageUrls));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "일부 또는 전체 이미지 업로드 실패", "message", e.getMessage()));
        }
    }
}