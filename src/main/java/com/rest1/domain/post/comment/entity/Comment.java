package com.rest1.domain.post.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity {

    private String content;
    @ManyToOne
    //@Getter(AccessLevel.PRIVATE)    // 외부에서 접근 불가 -> JSON에서 데이터를 뽑을때 content는 뽑을수 있지만 post는 뽑을수 없게됨
    @JsonIgnore // JSON에서 데이터를 뽑을때 이건 무시하라는 이야기 -> @Getter(AccessLevel.PRIVATE)과 같은 효과인데 comment.getpost()는 가능해짐
    private Post post;

    public void update(String content) {
        this.content = content;
    }
}