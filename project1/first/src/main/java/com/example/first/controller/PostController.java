package com.example.first.controller;


import com.example.first.entity.Post;
import com.example.first.request.PostCreate;
import com.example.first.response.PostResponse;
import com.example.first.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor // final field 생성자 생성 후 autowired
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request/*, BindingResult result*/)  {
        postService.write(request);
    }

    @GetMapping("/posts/{id}")
    public PostResponse get(@PathVariable Long id){
        return postService.get(id);
    }
    @GetMapping("/posts")
    public List<PostResponse> getList(@RequestParam(defaultValue = "1") int page){

        return postService.getList(page);
    }
}
/**
 *  / posts 글 전체 조회
 *  / posts/{postId} - > 글 하나만 조회
 */
// Request(DTO)클래스
// Response 클래스

// POST 요청은 통상 200, 201응답
// CASE1 : 저장된 데이더 Entity -> response로 응답하기
// CASE2 : 저장한 데이터의 Primary_id -> response로 응답
//        -> Client에서는 수신한 id값으로 글 조회 API를 통해 데이터를 수신받음
// CASE3 : 응답 필요 없음 -> 클라이언트에서 모든 POST 데이터 context를 잘 관리함
// BAD CASE : 서버에서 반드시 어떤 형태를 정하여 Fix
//          -> 서버에서는 유연하게 대응하는 것이 좋음
//          -> 한번에 일괄적으로 잘 처리되는 케이스는 없음 -> 잘 관리하는 형태가 중요!

//    @PostMapping("/posts")
//    public String get(String title, String content){
//        log.info("title={}, content={}",title, content);
//        return "hello";
//    }
//    @PostMapping("/posts")
//    public String get(@RequestParam  Map<String, String> params){
//        log.info("params={}",params);
//        String title = params.get("title");
//        log.info("title={}", title);
//        return "hello";
//    }
//        if(result.hasErrors()){
//            List<FieldError> fieldErrorList = result.getFieldErrors();
//            FieldError firstFieldError = fieldErrorList.get(0);
//            String fieldName =  firstFieldError.getField();
//            String errorMessage = firstFieldError.getDefaultMessage();
//
//            Map<String, String> error = new HashMap<>();
//            error.put(fieldName, errorMessage);
//            return error;
//        }

//        String title = postCreate.getTitle();
//        String content = postCreate.getContent();

        //데이터를 검증하는 이류
        //1. client 개발자가 깜빡했을 수 있다. 실수로 값을 안보낼 수 있다.
        //2. client bug로 값이 누락 될 수 있음
        //3. 외부에서 임의로 값을 조작해서 보낼 수 있음
        //4. DB에 값을 저장할 때 의도치 않은 오류가 발생 할 수 있다.
        //5. 서버 개발자의 편안함을 위해서

        // 아래와 같은 작업은 반복가능 성이 큼
        // 반복을 3번이상하게 될 시 잘못된 것이 아닌지 고민!!(하드코딩ㄴㄴ)
        // 누락되는 사항이 생길 수 있음
        // 생각보다 검증해야할 것이 많음(꼼꼼해야함) !!중요
        // 뭔가 개발자 스럽지 않다!!
//        if(title == null || title.equals("")){
//            throw new Exception("타이틀 값이 없습니다.");
//        }
//        log.info("title={}", title);
//        log.info("content={}", content);
//        return "hello";
//    }
//}
