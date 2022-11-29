package com.example.studentscoremanagerbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class InforUpdateStudentRequest {
    private int id;
    private String name;
    private String numberPhone;
    private Date birthday;
}
