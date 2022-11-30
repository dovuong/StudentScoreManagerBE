package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings({"checkstyle:Indentation", "checkstyle:FileTabCharacter"})

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT T FROM User T WHERE T.status = true and T.username=:username")
    public User findUserByUsername(@Param("username") String username);
    @Query("SELECT T FROM User T WHERE T.status = true and T.id=:id")
    public User findUserById(@Param("id")int id);
    @Query("SELECT T FROM User T WHERE T.status = true and T.role.id=2")
    public List<User> findAll();



}

