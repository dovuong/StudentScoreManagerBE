package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    public List<Student> findAllByClassRoomId(int id);
    public Student findStudentById(int id);
    public Student findStudentByIdAndClassRoomId(int id, int idclass);
}
