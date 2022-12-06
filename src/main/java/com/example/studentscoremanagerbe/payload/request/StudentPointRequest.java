package com.example.studentscoremanagerbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentPointRequest {
    private String point;
    private int course_id;
    private int student_id;
}
