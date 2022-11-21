package com.example.studentscoremanagerbe.model;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings("checkstyle:Indentation")
@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255, message = "must be less than 255 characters")
    private String name;
    @Column
    @NotNull
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    User teacher;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;
    @JoinColumn(name = "createdAt", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createAt;

    @Column(name = "updatedAt", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateAt;


    @Column(name = "deletedAt", updatable = true)

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date deletedAt;

}
