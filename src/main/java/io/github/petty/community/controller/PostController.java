package io.github.petty.community.controller;

import io.github.petty.community.dto.PostDetailResponse;
import io.github.petty.community.dto.PostRequest;
import io.github.petty.community.service.PostService;
import io.github.petty.users.dto.CustomUserDetails;
import io.github.petty.users.entity.Users;
import io.github.petty.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UsersRepository usersRepository;

    // ✅ 게시글 생성
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostRequest request,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        Users user = usersRepository.findByUsername(username);
        Long id = postService.save(request, user, request.getImageUrls());
        return ResponseEntity.ok(Map.of("id", id));
    }

    // ✅ 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody PostRequest request,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        Users user = usersRepository.findByUsername(username);
        postService.update(id, request, user);
        return ResponseEntity.ok().build();
    }

    // ✅ 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        Users user = usersRepository.findByUsername(username);
        postService.delete(id, user);
        return ResponseEntity.ok().build();
    }

    // ✅ 게시글 목록 (타입별 + 페이징)
    @GetMapping
    public ResponseEntity<Page<?>> getAllByType(@RequestParam("type") String type,
                                                @PageableDefault(size = 9) Pageable pageable) {
        Page<?> posts = postService.findAllByType(type, pageable);
        return ResponseEntity.ok(posts);
    }

    // ✅ 게시글 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long id) {
        PostDetailResponse post = postService.findById(id);
        return ResponseEntity.ok(post);
    }
}
