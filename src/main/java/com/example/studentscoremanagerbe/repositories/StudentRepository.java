package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT T FROM Student T WHERE T.status = true and T.classRoom.id=:id")
    public List<Student> findAllByClassRoomId(@Param("id") int id);
    @Query("SELECT T FROM Student T WHERE T.status = true ")
    public List<Student> findAll();
    public Student findStudentById(int id);
    @Query("SELECT T FROM Student T WHERE T.status = true and T.classRoom.id=:idclass and T.id=:id")
    public Student findStudentByIdAndClassRoomId(@Param("id")int id,@Param("idclass") int idclass);
}
