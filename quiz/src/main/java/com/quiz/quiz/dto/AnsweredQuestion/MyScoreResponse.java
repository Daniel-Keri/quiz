package com.quiz.quiz.dto.AnsweredQuestion;

import lombok.Data;

@Data
public class MyScoreResponse {

    private String theme;

    private Double score;

    public MyScoreResponse(String theme, Double score) {

        this.theme = theme;
        this.score = score;
    }
}
