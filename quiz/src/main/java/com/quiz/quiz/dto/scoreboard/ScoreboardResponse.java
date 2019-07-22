package com.quiz.quiz.dto.scoreboard;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain=true)
@NoArgsConstructor
public class ScoreboardResponse {

    //private UUID id;

    private String theme;

    private Double score;

    private String username;

    //private UUID userId;
}
