package com.example.studentscoremanagerbe.payload.request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListTeacherRequest {
    private List<CreateTeacherRequest> teacherRequests;
}
