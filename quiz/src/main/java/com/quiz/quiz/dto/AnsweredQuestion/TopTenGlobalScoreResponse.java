package com.quiz.quiz.dto.AnsweredQuestion;

import lombok.Data;

@Data
public class TopTenGlobalScoreResponse {

    private String username;

    private Double score;

    public TopTenGlobalScoreResponse(String username, Double score) {

        this.username = username;
        this.score = score;
    }
}
