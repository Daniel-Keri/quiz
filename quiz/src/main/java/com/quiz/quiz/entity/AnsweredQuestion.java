package com.quiz.quiz.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "user_account_id")
    private UUID userAccountId;

    @Column(name = "chosen_answer_id")
    private UUID chosenAnswerId;
}
