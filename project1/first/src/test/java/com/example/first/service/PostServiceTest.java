package com.example.first.service;

import com.example.first.entity.Post;
import com.example.first.exception.PostNotFound;
import com.example.first.repository.PostRepository;
import com.example.first.request.PostCreate;
import com.example.first.request.PostEdit;
import com.example.first.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureMockMvc
@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
        // 각 테스트(메서드)가 진행 될 때 마다 실행되는 method
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        //when
        postService.write(postCreate);

        //then
        Assertions.assertEquals(1L, postRepository.count());
        assertEquals(1, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 한개 조회")
    void test2() throws Exception {
        //given
        Post requestPost = Post.builder()
                .title("123456789012345")
                .content("bar").build();
        postRepository.save(requestPost);
        // 클라이언트 요구사항
        //json 응답 title 값을 10글자 이내로
        //  클라이언트 요구를 위한 서비스 정책에 맞게
        // 응답 클래스를 분리!(entity, service 수정등 절대 하지 말것!)
        // 이유는 entity 출력값조회시 용도에 맞지않는 응답이 나올 수 있음
        // service 구현시 요구사항에 맞추려면 너무많은 service가 생성될 수 있음
        //when
        PostResponse response = postService.get(requestPost.getId());
        //then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, postRepository.count());
        Assertions.assertEquals(requestPost.getId(), response.getId());
        Assertions.assertEquals("1234567890", response.getTitle());
        Assertions.assertEquals("bar", response.getContent());
    }

    @Test
    @DisplayName("글 1page 조회")
    void test3() throws Exception {
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder().title("제목" + i).content("내용" + i)
                .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);
//        Post requestPost1 = Post.builder().title("foo1").content("bar1").build();
//        postRepository.save(requestPost1);
//        Post requestPost2 = Post.builder().title("foo2").content("bar2").build();
//        postRepository.save(requestPost2);
        // 클라이언트 요구사항
        //json 응답 title 값을 10글자 이내로
        //  클라이언트 요구를 위한 서비스 정책에 맞게
        // 응답 클래스를 분리!(entity, service 수정등 절대 하지 말것!)
        // 이유는 entity 출력값조회시 용도에 맞지않는 응답이 나올 수 있음
        // service 구현시 요구사항에 맞추려면 너무많은 service가 생성될 수 있음
        //when
        List<PostResponse> posts = postService.getList(1);

        //then
        Assertions.assertNotNull(posts);
        assertEquals(10, posts.size());
        Assertions.assertEquals("제목30",posts.get(0).getTitle());
        Assertions.assertEquals("내용30",posts.get(0).getContent());
//        Assertions.assertEquals("제목1", list.get(0).getTitle());
//        Assertions.assertEquals("내용1", list.get(0).getContent());
//        Assertions.assertEquals("제목2", list.get(1).getTitle());
//        Assertions.assertEquals("내용2", list.get(1).getContent());
    }
    @Test
    @DisplayName("글 수정 조회")
    void test4() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();


        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목1")
                .content("내용1")
                .build();
        //when
        postService.edit(post.getId(), postEdit);

        //then
       Post chagedPost =  postRepository.findById(post.getId());
        Assertions.assertNotNull(chagedPost);
        Assertions.assertEquals("제목1", chagedPost.getTitle());
        Assertions.assertEquals("내용1", chagedPost.getContent());
    }
    @Test
    @DisplayName("글 삭제")
    void test5() throws Exception {
        //given
        Post post1 = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        Post post2 = Post.builder()
                .title("제목")
                .content("내용")
                .build();


        postRepository.save(post1);
        postRepository.save(post2);
        //when
        postService.delete(post1.getId());
        postService.delete(post2.getId());

        //then
        Assertions.assertEquals(0, postRepository.count());
    }
    @Test
    @DisplayName("글 1개 조회")
    void test6() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);
        //expected
        assertThrows(PostNotFound.class, ()->{
            postService.get(post.getId()+1L);
        });
    }
    @Test
    @DisplayName("글 삭제 - 존재하지 않는 글")
    void test7() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        //when
        postRepository.delete(post.getId()+1L);
        //expected
        assertThrows(PostNotFound.class, ()->{
            postService.get(post.getId()+1L);
        });
    }
    @Test
    @DisplayName("글 삭제 - 존재하지 않는 글")
    void test8() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        //when
        //expected
        assertThrows(PostNotFound.class, ()->{
            postService.delete(post.getId()+1L);
        });
    }
    @Test
    @DisplayName("글 수정 조회 - 존재하지 않는 글")
    void test9() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();


        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목1")
                .content("내용1")
                .build();
        //when
        // expected
        assertThrows(PostNotFound.class, ()->{
            postService.edit(post.getId()+1L, postEdit);
        });
    }
}