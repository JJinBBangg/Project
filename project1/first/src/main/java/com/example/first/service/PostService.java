package com.example.first.service;


import com.example.first.entity.Post;
import com.example.first.repository.PostRepository;
import com.example.first.request.PostCreate;
import com.example.first.request.PostEdit;
import com.example.first.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor // final로 생성된 field의 생성자 자동 autowired
public class PostService {

    private final PostRepository postRepository;


    public void write(PostCreate postCreate){
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
//                new Post(postCreate.getTitle(), postCreate.getContent());
        postRepository.save(post);
    }

    public PostResponse get(Long id)  {
        return new PostResponse(postRepository.findById(id));
    }

    public List<PostResponse> getList(int page) {
        return postRepository.findPage(page).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
    public void edit(Long id, PostEdit postEdit){
        Post post =postRepository.findById(id);
        post.setTitle("");
        post.setContent("");
    }
}
    // 데이터 양이 많은 경우 -> 비용이 너무 많이 든다
    // 글이 100,000,000 -> DB글 모두 조회하는 경우 -> DB가 뻗을 수 있다.
    // DB -> 애플리캐이션 서버로 전달하는 시간, 트래픽비용 등이 많이 발생할 수 있다.
        /**
         * Controller -> WebPostService(Client와 소통 응답)      -> Repository
         *               PostService(외부 서비스와 연동되는 서비스)
         */
//                .map(post -> new PostResponse(post))
//                .map(post->{ PostResponse.builder()
//                        .id(post.getId())
//                        .title(post.getTitle())
//                        .content(post.getContent())
//                        .build()})
