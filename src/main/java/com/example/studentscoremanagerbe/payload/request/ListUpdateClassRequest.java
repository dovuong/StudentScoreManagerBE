package com.example.studentscoremanagerbe.payload.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListUpdateClassRequest {
    private List<NameRequest> nameClassroom;
    private int idFaculty;
}
