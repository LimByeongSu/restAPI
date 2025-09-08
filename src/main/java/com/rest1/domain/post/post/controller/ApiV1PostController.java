package com.rest1.domain.post.post.controller;

import com.rest1.domain.post.post.dto.PostDto;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController //-> 이걸 쓰면 @ResponseBody를 생략가능
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")    //모든 Mapping에 "/api/v1/posts"가 추가됨 
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping
    @Transactional(readOnly = true) //단순 조회는 readOnly를 붙여주자
    //다건 조회
    public List<PostDto> getItems() {
        return postService.findAll().stream()
                .map(PostDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true) //단순 조회는 readOnly를 붙여주자
    //단건 조회
    public PostDto getItem(@PathVariable Long id) {
        Post post = postService.findById(id).get();
        return  new PostDto(post);
    }

}
