package com.example.studentscoremanagerbe.payload.response;

import com.example.studentscoremanagerbe.model.Course;
import com.example.studentscoremanagerbe.model.Subject;
import com.example.studentscoremanagerbe.model.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponse {
    private int id;

    private String name;

    private String teacherName;

    private String subjectName;

    private Integer totalStudent;

    public CourseResponse(Course course, Integer totalStudent) {
        id = course.getId();
        name = course.getName();
        teacherName = course.getTeacher().getName();
        subjectName = course.getSubject().getName();
        this.totalStudent = totalStudent;
    }


}
