package com.quiz.quiz.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

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

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "theme", nullable = false)
    private String theme;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();


    @Column(name = "score")
    private double score;

}
