package com.quiz.quiz.dto.scoreboard;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ScoreboardRequest {

    String theme;
}
