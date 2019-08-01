package com.quiz.quiz.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table( name = "user_account")
//@DiscriminatorValue(value = "user_account")
public class UserAccount extends Account {

//    @ElementCollection
//    @MapKeyColumn(name = "question_id")
//    @Column(name = "score")
//    @CollectionTable(name = "answered_questions")
//    private Map<UUID, Dounble> answeredQuestions;
}