package com.rest1.domain.post.post.controller;

import com.rest1.domain.post.post.dto.PostDto;
import com.rest1.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
//@RestController -> 이걸 쓰면 @ResponseBody를 생략가능
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")    //모든 Mapping에 "/api/v1/posts"가 추가됨 
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping
    @Transactional(readOnly = true)
    @ResponseBody
    public List<PostDto> list() {
        return postService.findAll().stream()
                .map(post -> new PostDto(post) )
                .toList();
    }
}
