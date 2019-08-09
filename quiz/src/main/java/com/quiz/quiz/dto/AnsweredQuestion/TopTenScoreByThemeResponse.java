package com.quiz.quiz.dto.AnsweredQuestion;

import lombok.Data;

import java.util.UUID;

@Data
public class TopTenScoreByThemeResponse {

    private Double score;
    private String username;
    private UUID userAccountId;

    public TopTenScoreByThemeResponse(Double score, String username, UUID userAccountId) {

        this.score = score;
        this.username = username;
        this.userAccountId = userAccountId;
    }
}
