package com.felipeantunes.Zimbaue.repository;

import com.felipeantunes.Zimbaue.interfaces.IPostDTO;
import com.felipeantunes.Zimbaue.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT \n" +
            "\tp.id `postId`,\n" +
            "    p.title `postTitle`,\n" +
            "    p.`description` `postDescription`,\n" +
            "    p.link `postLink`,\n" +
            "    p.created_at `postDate`,\n" +
            "    u.id `userId`,\n" +
            "    u.`name` `userName`,\n" +
            "    u.email `userEmail`,\n" +
            "    u.`role` `userRole`\n" +
            "FROM post `p`\n" +
            "INNER JOIN `user` `u` ON p.user_id = u.id;", nativeQuery = true)
    List<IPostDTO> getPostList();

    @Query(value = "SELECT \n" +
            "\tp.id `postId`,\n" +
            "    p.title `postTitle`,\n" +
            "    p.`description` `postDescription`,\n" +
            "    p.link `postLink`,\n" +
            "    p.created_at `postDate`,\n" +
            "    u.id `userId`,\n" +
            "    u.`name` `userName`,\n" +
            "    u.email `userEmail`,\n" +
            "    u.`role` `userRole`\n" +
            "FROM post `p`\n" +
            "INNER JOIN `user` `u` ON p.user_id = u.id\n" +
            "WHERE p.id = :id;\n", nativeQuery = true)
    IPostDTO getPostById(@PathVariable Integer id);
}
