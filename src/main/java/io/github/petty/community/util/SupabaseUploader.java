package io.github.petty.community.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SupabaseUploader {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    private static final String BUCKET_NAME = "post-images";

    public String upload(MultipartFile file) throws IOException {
        // 1. 고유한 파일 이름 생성
        String filename = UUID.randomUUID() + "." + getExtension(file.getOriginalFilename());
        String uploadUrl = supabaseUrl + "/storage/v1/object/" + BUCKET_NAME + "/" + filename;

        // 2. 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Authorization", "Bearer " + supabaseKey);

        // 3. 요청 본문
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(file.getBytes(), headers);

        // 4. 업로드 요청
        ResponseEntity<String> response = restTemplate.exchange(
                uploadUrl,
                HttpMethod.PUT,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
            return supabaseUrl + "/storage/v1/object/public/" + BUCKET_NAME + "/" + filename;
        } else {
            throw new RuntimeException("이미지 업로드 실패: " + response.getStatusCode());
        }
    }

    private String getExtension(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
    }
}
