package com.quiz.quiz.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name = "answered_questions")
@Accessors(chain = true)
public class AnsweredQuestion {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private UUID questionId;

    @NotNull
    @Column(name = "user_account_id", nullable = false)
    private UUID userAccountId;

    @NotNull
    @Column(name = "chosen_answer_id", nullable = false)
    private UUID chosenAnswerId;

    @NotNull
    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;

    @NotNull
    @Column(name = "points", nullable = false)
    private Double points;
}
