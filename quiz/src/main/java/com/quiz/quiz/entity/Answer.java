package com.quiz.quiz.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table( name = "answer")
@Accessors(chain = true)
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private String image;

    @Column(name = "is_correct",nullable = false)
    private Boolean isCorrect;
}
