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

//    @elementcollection
//    @mapkeycolumn(name = "question_id")
//    @column(name = "score")
//    @collectiontable(name = "answered_questions")
//    private map<uuid, double> answeredquestions;

}