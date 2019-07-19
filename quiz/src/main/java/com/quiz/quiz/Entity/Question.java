package com.quiz.quiz.Entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name ="question")
@Accessors(chain = true)
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "text,nullable = false")
    private String text;

    @Column(name = "theme",nullable = false)
    private String theme;

    @Column(name = "answers",nullable = false)
    private List<String> answers;


    @Column(name = "points")
    private double points;

}
