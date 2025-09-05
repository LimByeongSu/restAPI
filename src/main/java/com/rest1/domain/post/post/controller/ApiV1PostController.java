package com.rest1.domain.post.post.controller;

import com.rest1.domain.post.post.entity.Post;
import com.rest1.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
//@RestController -> 이걸 쓰면 @ResponseBody를 생략가능
@Controller
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping("/api/v1/posts")
    @Transactional(readOnly = true)
    @ResponseBody
    public List<Post> list() {
        //더이상 templates방식을 쓰지 않는다.
        return  postService.findAll();
    }
}
