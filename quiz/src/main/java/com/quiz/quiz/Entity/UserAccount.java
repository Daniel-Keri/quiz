package com.quiz.quiz.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table( name = "user_account")
public class UserAccount extends Account {

    @ElementCollection
    @MapKeyColumn(name = "question_id")
    @Column(name = "score")
    @CollectionTable(name = "answered_questions")
    private Map<UUID, Question> answeredQuestions;
}
