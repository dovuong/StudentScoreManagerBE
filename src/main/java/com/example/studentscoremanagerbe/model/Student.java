package com.example.studentscoremanagerbe.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max=255, message = "must be less than 255 characters")
    private String name;
    @Column
    @NotNull
    @Size(max=255, message = "must be less than 255 characters")
    private String numberPhone;
    @Column
    @NotNull
    private Date birthday;
    @Column
    @NotNull
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "class_id")
    ClassRoom classRoom;



}
