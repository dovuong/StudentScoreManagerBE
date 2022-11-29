package com.example.studentscoremanagerbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteStudentRequest {
    private int idClass;
    private int idStudent;
}
