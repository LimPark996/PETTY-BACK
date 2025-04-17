package io.github.petty.community.repository.comment;

import io.github.petty.community.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 1. 댓글 목록 조회 (작성 시간순 정렬)
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);

    // 2. 댓글 작성 → save(Comment comment) (JpaRepository 기본 제공)

    // 3. 댓글 삭제 → deleteById(Long id) (JpaRepository 기본 제공)

    // 4. 본인 댓글인지 확인 (삭제 권한 확인용)
    boolean existsByIdAndUserId(Long id, UUID userId);
}
