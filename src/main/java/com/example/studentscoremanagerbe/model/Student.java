package com.example.studentscoremanagerbe.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "Student")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255, message = "must be less than 255 characters")
    private String name;
    @Column
    @NotNull
    @Size(max = 255, message = "must be less than 255 characters")
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
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;






}
