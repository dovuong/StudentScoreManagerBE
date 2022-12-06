package com.example.studentscoremanagerbe.payload.response;

import com.example.studentscoremanagerbe.model.Course;
import com.example.studentscoremanagerbe.model.Student;
import com.example.studentscoremanagerbe.model.StudentPoint;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class StudentPointResponse {
    private int id;

    private String point;

    Student student;

    CourseResponse course;

    public StudentPointResponse(StudentPoint studentPoint){
        id = studentPoint.getId();
        point = studentPoint.getPoint();
        student = studentPoint.getStudent();
        course = new CourseResponse(studentPoint.getCourse());
    }


}