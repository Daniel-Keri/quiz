package com.quiz.quiz.dto.AnsweredQuestion;

import lombok.Data;

@Data
public class TopTenScoreByThemeResponse {

    private String username;

    private Double score;

    public TopTenScoreByThemeResponse(String username, Double score) {

        this.username = username;
        this.score = score;
    }
}
