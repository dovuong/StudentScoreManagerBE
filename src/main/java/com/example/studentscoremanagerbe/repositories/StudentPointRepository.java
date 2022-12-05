package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.StudentPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPointRepository extends JpaRepository<StudentPoint, Integer> {
    public List<StudentPoint> findAllByCourseId(int id);
    public StudentPoint findStudentPointByCourse_IdAndAndStudentId(int course_id, int student_id);
}
