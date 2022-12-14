package com.example.studentscoremanagerbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClassroomRequest {
    private String nameClassRoom;
    private int idClass;
    private int idFaculty;
}
