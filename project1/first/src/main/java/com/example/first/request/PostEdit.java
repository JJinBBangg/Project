package com.example.first.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PostEdit {
    @NotBlank(message = "타이틀이 입력하세요.")
    public String title;
    @NotBlank(message = "콘텐츠가 없습니다.")
    public String content;
    @Builder
    public PostEdit(long id, String title, String content) {
        this.title = title;
        this.content = content;
    }
}
