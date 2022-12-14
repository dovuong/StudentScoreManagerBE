package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.Course;
import com.example.studentscoremanagerbe.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    public List<Subject> findAll();

    public Subject findById(int id);

}
