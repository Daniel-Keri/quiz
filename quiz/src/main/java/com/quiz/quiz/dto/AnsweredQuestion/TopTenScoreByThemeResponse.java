package com.quiz.quiz.dto.AnsweredQuestion;

import lombok.Data;

@Data
public class TopTenScoreByThemeResponse {

    private Double score;

    private String username;

    public TopTenScoreByThemeResponse(Double score, String username) {

        this.score = score;
        this.username = username;
    }
}
