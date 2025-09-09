package com.rest1.domain.post.post.controller;

import com.rest1.domain.post.post.dto.PostDto;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.domain.post.post.service.PostService;
import com.rest1.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{id}")
    @Transactional
    //글 삭제
    public RsData<Void> deleteItem(@PathVariable Long id) {
        Post post = postService.findById(id).get();
        postService.delete(post);

        RsData<Void> rsData = new RsData<>(
                "204-1",
                "%d번 게시물이 삭제되었습니다".formatted(id)
                );

        return rsData;
    }

    //들어온 JSON 데이터를 읽기위한 form데이터(Dto와 맥락이 같다)
    record PostWriteReqBody(
            @NotBlank
            @Size(min = 2, max = 10)
            String title,
            @NotBlank
            @Size(min = 2, max = 100)
            String content
    ){

    }

    @PostMapping
    public ResponseEntity<RsData<PostDto>> createItem(//원래 url에서 값을 가져오는거였으면 @RequestParam, @PathVariable을 썼지만
                                                      //이제는 아니다. JSON으로 받는다.
          @RequestBody @Valid ApiV1PostController.PostWriteReqBody form // @ResponseBody 랑 다르다.
    ){
        Post post = postService.write(form.title, form.content);
        RsData<PostDto> reData = new RsData<>(
                "201 - 1",
                "%d번 게시물이 생성되었습니다.".formatted(post.getId()),
                new PostDto(post)
        );

        return new ResponseEntity<>(reData, HttpStatus.CREATED);

    }
}
