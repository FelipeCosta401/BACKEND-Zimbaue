package com.felipeantunes.Zimbaue.repository;

import com.felipeantunes.Zimbaue.interfaces.IUserDTO;
import com.felipeantunes.Zimbaue.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "SELECT \n" +
            "     id,\n" +
            "    `name`,\n" +
            "    email,\n" +
            "    `role`,\n" +
            "    created_at `createdAt`\n" +
            "FROM user;", nativeQuery = true)
    List<IUserDTO> getUserList();

    @Query(value = "SELECT \n" +
            "\tid,\n" +
            "    `name`,\n" +
            "    email,\n" +
            "    `role`,\n" +
            "    created_at `createdAt`\n" +
            "FROM user\n" +
            "WHERE user.id = :id;", nativeQuery = true)
    IUserDTO getUserById(@Param("id") Integer id);
}
