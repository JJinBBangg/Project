package com.example.first.repository;


import com.example.first.entity.Post;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostRepository {


    Post save(Post post);

    int count();

    @Select("""
            SELECT COUNT(ID) count FROM POST
            """)

    int deleteAll();

    List<Post> findAll();

    Post findById(Long id);

    List<Post> saveAll(List<Post> posts);
}
