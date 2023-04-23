//package com.example.first.controller;
//
//import ch.qos.logback.core.boolex.Matcher;
//import com.example.first.entity.Post;
//import com.example.first.repository.JDBCPostRepository;
//import com.example.first.request.PostCreate;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//class PostControllerTest {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private JDBCPostRepository jdbcPostRepository;
//
//    @BeforeEach // 각 테스트(메서드)가 진행 될 때 마다 실행되는 method
//    void clean (){
//        jdbcPostRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("/posts 요청 Post를 DB에 저장한다.")
//    void test1() throws  Exception{
//
//        //given
//        PostCreate request = PostCreate.builder()
//                .id(0)
//                .title("제목입니다.")
//                .content("내용입니다.")
//                .build();
//        String json = objectMapper.writeValueAsString(request);
//
//        //excepted
//        mockMvc.perform(post("/posts")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(""))
////                .andExpect(MockMvcResultMatchers.content().json(json))
//                .andDo(MockMvcResultHandlers.print());
//    }
//    @Test
//    @DisplayName("/posts 요청 시 title값은 필수다.")
//    void test2() throws  Exception{
//
//        //given
//        PostCreate request = PostCreate.builder()
//                .content("내용입니다.")
//                .build();
//        String json = objectMapper.writeValueAsString(request);
//
//        //excepted
//        mockMvc.perform(post("/posts")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(jsonPath("$.code").value("400"))
//                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
//                .andExpect(jsonPath("$.validation.title").value("타이틀이 입력하세요."))
//                .andDo(MockMvcResultHandlers.print());
//    }
//    @Test
//    @DisplayName("/posts 요청 시 DB에 Post 래코드 추가.")
//    void test3() throws  Exception{
//        //given
//        PostCreate request = PostCreate.builder()
//                .title("제목입니다.")
//                .content("내용입니다.")
//                .build();
//        String json = objectMapper.writeValueAsString(request);
//
//        //when
//        mockMvc.perform(post("/posts")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        //then
//        assertEquals(1,jdbcPostRepository.count());
//        Post post = jdbcPostRepository.findAll().get(0);
//        assertEquals("제목입니다.", post.getTitle());
//        assertEquals("내용입니다.", post.getContent());
//    }
//
//    @Test
//    @DisplayName("글 1개 조회")
//    void test4() throws Exception {
//        //iven
//        Post post = Post.builder()
//                .title("123456789012345")
//                .content("bar")
//                .build();
//        jdbcPostRepository.save(post);
//        //expected
//        mockMvc.perform(get("/posts/{}")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(jsonPath("$.id").value(post.getId()))
//        .andExpect(jsonPath("$.title").value("1234567890"))
//        .andExpect(jsonPath("$.content").value("bar"))
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    @DisplayName("글 여러개 조회")
//    void test5() throws Exception {
//        //iven
//        Post post1 = Post.builder()
//                .title("123456789012345")
//                .content("bar1")
//                .build();
//        jdbcPostRepository.save(post1);
//        Post post2 = Post.builder()
//                .title("123456789012345")
//                .content("bar1")
//                .build();
//        jdbcPostRepository.save(post2);
//        //expected
//        mockMvc.perform(get("/posts")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                /**
//                 * 단건조회인 경우 아래 json type으로 객체가 전달됭
//                 * {id:..., title:...}
//                 */
//                /**
//                 * 여러건 조회인 경우 List type으로 객체 전달됨
//                 * [{id:..., title:...}, {id:..., title:...}]
//                 */
//
////                .andExpect(jsonPath("$.title").value("1234567890"))
////                .andExpect(jsonPath("$.content").value("bar"))
//                .andExpect(jsonPath("$.length()", Matchers.is(2)))
//                .andExpect(jsonPath("$[0].title").value("1234567890"))
//                .andExpect(jsonPath("$[0].content").value("bar1"))
//                .andExpect(jsonPath("$[1].title").value("1234567890"))
//                .andExpect(jsonPath("$[1].content").value("bar1"))
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//}