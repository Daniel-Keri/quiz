package com.quiz.quiz.Entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table( name = "scoreboard")
@Accessors(chain = true)
public class Scoreboard {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "score")
    private Double score;

    @Column(name = "username")
    private String username;

    @Column(name = "user_id")
    private UUID userId;
}
