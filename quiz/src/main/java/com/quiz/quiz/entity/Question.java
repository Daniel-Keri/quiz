package com.quiz.quiz.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

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

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;

    @NotNull
    @Column(name = "theme", nullable = false)
    private String theme;

//    @NotNull
//    @OneToMany(cascade = {PERSIST, REFRESH})
//    @JoinColumn(name = "question_id", nullable = false)
//    private List<Answer> answers = new ArrayList<>();

    @Column(name = "image", columnDefinition = "text")
    private String image;

    @NotNull
    @Column(name = "points", nullable = false)
    private Double points;

}
