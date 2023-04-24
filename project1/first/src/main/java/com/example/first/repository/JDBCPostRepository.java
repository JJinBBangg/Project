//package com.example.first.repository;
//
//
//import com.example.first.entity.Post;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Repository
//public class JDBCPostRepository implements PostRepository{
//
//    private final DataSource dataSource;
//
//    @Override
//    public Post save(Post post) {
//        String sql = """
//                INSERT INTO Post(title, content)
//                VALUES(?, ? )
//                """;
//        try (Connection con = dataSource.getConnection();
//               PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, post.getTitle());
//            st.setString(2, post.getContent());
//            st.executeUpdate();
//        } catch(Exception e) {
//              e.printStackTrace();
//        }
//        return post;
//
//    }
//
//    @Override
//    public int count() {
//        String sql = """
//                SELECT COUNT(ID) count FROM POST
//                """;
//        int result = 0;
//        try (Connection con = dataSource.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql)) {
//            rs.next();
//            result = rs.getInt("count");
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public int deleteAll() {
//        String sql ="DELETE FROM POST";
//        int result = 0;
//
//        try (Connection con = dataSource.getConnection();
//                Statement st = con.createStatement()) {
//            result = st.executeUpdate(sql);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public List<Post> findAll() {
//        List<Post> postList = new ArrayList<>();
//        String sql ="SELECT * FROM POST";
//        try (Connection con = dataSource.getConnection();
//             Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//            while(rs.next()){
//                Post post = new Post();
//                post.setId(rs.getInt("id"));
//                post.setTitle(rs.getString("title"));
//                post.setContent(rs.getString("content"));
//                postList.add(post);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    return postList;
//    }
//
//    @Override
//    public Post findById(Long id){
//        String sql = """
//                SELECT * FROM POST WHERE id = ?
//                """;
//        List<Post> list = new ArrayList<>();
//        try (Connection con = dataSource.getConnection();
//             PreparedStatement st = con.prepareStatement(sql)) {
//            st.setLong(1, id);
//            ResultSet rs = st.executeQuery();
//            while(rs.next()) {
//                Post post = Post.builder()
//                        .id(rs.getLong("id"))
//                        .title(rs.getString("title"))
//                        .content(rs.getString("content"))
//                        .build();
//                list.add(post);
//            }
//            rs.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return list.get(0);
//    }
//
//    @Override
//    public int saveAll(List<Post> posts) {
//
//        return 0;
//    }
//}
