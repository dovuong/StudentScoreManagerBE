package com.example.studentscoremanagerbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ListUpdateStudentRequest {
    private int idClassroom;
    private List<InforUpdateStudentRequest> student;
}
