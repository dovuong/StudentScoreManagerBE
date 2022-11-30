package com.example.studentscoremanagerbe.payload.request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateTeacherRequest {
    private String name;
    private String numberPhone;
    private Date birthday;
}
