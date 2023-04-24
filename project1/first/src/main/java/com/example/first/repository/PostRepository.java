package com.example.first.repository;


import com.example.first.entity.Post;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostRepository {


    int save(Post post);

    int count();

    int deleteAll();

    List<Post> findPage(int page);

    List<Post> findAll();

    Post findById(Long id);

    void saveAll(List<Post> posts);
}
