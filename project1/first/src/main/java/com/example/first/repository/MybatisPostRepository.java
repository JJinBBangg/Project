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
    @Options(useGeneratedKeys = true, keyColumn="id", keyProperty="id")
    int save(Post post);

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
            ORDER BY 1 DESC
            OFFSET ${(page - 1) * 10} ROWS FETCH NEXT 10 ROWS ONLY
            """)
    List<Post> findPage(int page);
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
            INSERT INTO POST(title, content) 
                <foreach collection="list" item="post" separator="UNION ALL">
                    SELECT #{post.title}, #{post.content} FROM DUAL 
                </foreach>
            </script>
            """)
    int saveAll(List<Post> list);

    @Override
    @Update("""
            UPDATE POST SET
                title = #{title},
                content = #{content}
            """)
    int edit(Post post);

    @Override
    @Delete("""
            DELETE FROM POST WHERE id = #{id}
            """)
    int delete(Long id);
}


