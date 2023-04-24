package com.example.first.response;


import com.example.first.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 서비스 정책에 맞는 응답 클래스 생성
 *
 */

@Getter
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;

    public PostResponse (Post post){
         this.id = post.getId();
         this.title = post.getTitle().substring(0,Math.min(post.getTitle().length(), 10));
         this.content = post.getContent();
    }

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0,Math.min(title.length(), 10));
        this.content = content;
    }

}
