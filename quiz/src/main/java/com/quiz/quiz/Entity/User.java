package com.quiz.quiz.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table( name = "user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class User extends Account {

    @Column(name = "answeredQuestions", nullable = false)
    private Map<UUID,Double> answeredQuestions;
}
