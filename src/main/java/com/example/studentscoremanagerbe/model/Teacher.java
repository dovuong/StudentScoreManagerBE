package com.example.studentscoremanagerbe.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Teacher {
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
    @Column
    @NotNull
    @Size(max=255, message = "must be less than 255 characters")
    private String username;
    @Column
    @NotNull
    @Size(max=255, message = "must be less than 255 characters")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
    @JoinColumn(name = "createdAt",nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createAt;

    @Column(name = "updatedAt",nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateAt;


    @Column(name = "deletedAt", updatable = true)

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date deletedAt;

}
