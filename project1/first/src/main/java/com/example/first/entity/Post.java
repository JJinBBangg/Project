package com.example.first.entity;


import com.example.first.request.PostEdit;
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

    public PostEditor.PostEditorBuilder toEditor(){
        return  PostEditor.builder()
                .title(title)
                .content(content);
    }

    public void edit(PostEditor postEditor) {
        this.title = postEditor.getTitle();
        this.content = postEditor.getContent();
    }
}
