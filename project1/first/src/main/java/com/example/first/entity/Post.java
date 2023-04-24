package com.example.first.entity;


import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

    private Long id;
    private String title;
    private String content;

    @Builder
    public Post(Long id,String title, String content) {
        this.id = id;
        this.title= title;
        this.content=content;
    }

}
