package com.rest1.domain.post.comment.controller;


import com.rest1.domain.post.comment.controller.dto.CommentDto;
import com.rest1.domain.post.comment.entity.Comment;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class ApiV1CommentController {

    private final PostService postService;

    //댓글 다건 조회(/posts/1/comments)  -> 1번글의 모든 댓글
    @GetMapping("/{postId}/comments")
    public List<CommentDto> getItems(
            @PathVariable Long postId
    ) {
        Post post = postService.findById(postId).get();
        return post.getComments().stream()
                .map(CommentDto::new)
                .toList();
    }

    //댓글 단건 조회(/posts/1/comments/2) -> 1번글의 2번 댓글
    @GetMapping("/{postId}/comments/{commentId}")
    @Transactional(readOnly = true)
    public CommentDto getItem(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        Post post = postService.findById(postId).get();
        Comment comment = post.findCommentById(commentId).get();
        return new CommentDto(comment);
    }

    @GetMapping("/{postId}/comments/{commentId}/delete")
    @Transactional
    //댓글 삭제
    public String deleteItem(
            @PathVariable Long postId,
            @PathVariable Long commentId
    )
    {
        Post post = postService.findById(postId).get();
        postService.deleteComment(post, commentId);
        return "%d번 댓글이 삭제되었습니다.".formatted(commentId);
    }
}
