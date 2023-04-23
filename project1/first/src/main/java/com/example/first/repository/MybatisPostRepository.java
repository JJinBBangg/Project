package com.example.first.repository;


import com.example.first.entity.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MybatisPostRepository extends PostRepository {


    @Override
    @Insert("""
            INSERT INTO Post(title, content)
            VALUES(#{title}, #{content} )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Post save(Post post);

    @Override
    @Select("""
            SELECT COUNT(ID) count FROM POST
            """)
    int count();

    @Override
    @Delete("""
            DELETE FROM POST
            """)
    int deleteAll();

    @Override
    @Select("""
            SELECT * FROM POST
            """)
    List<Post> findAll();

    @Override
    @Select("""
            SELECT * FROM POST WHERE id = #{id}
            """)
    Post findById(Long id);

    @Override
    @Insert("""
            <script>
            INSERT INTO Post(title, content) VALUES 
            
            <foreach collection="List" item="posts" separator=","> 
            (
                #{title}, 
                #{content}
            )
            </foreach>
            
            </script>
            """)
    List<Post> saveAll(List<Post> posts);
}


