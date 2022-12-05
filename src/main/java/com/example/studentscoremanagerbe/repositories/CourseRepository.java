package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    public List<Course> findAllByTeacherId(@Param("id") int id);

    public List<Course> findAllBySubjectId(@Param("id") int id);

    public List<Course> findAll();

    public Course findById(int id);

    public Course findStudentByIdAndClassRoomId(@Param("id")int id);
}
