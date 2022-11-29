package com.example.studentscoremanagerbe.model;

import com.sun.istack.NotNull;
import lombok.Getter;
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
@Table(name = "Users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Size(max = 255, message = "must be less than 255 characters")
    private String name;
    @Column
    @Size(max = 255, message = "must be less than 255 characters")
    private String numberPhone;
    @Column
    private Date birthday;
    @Column
    private boolean status;
    @Column
    @NotNull
    @Size(max = 255, message = "must be less than 255 characters")
    private String username;
    @Column
    @NotNull
    @Size(max = 255, message = "must be less than 255 characters")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;


}
