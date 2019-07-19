package com.quiz.quiz.Entity;

import com.quiz.quiz.Enums.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@MappedSuperclass
@Accessors(chain = true)
public abstract class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
