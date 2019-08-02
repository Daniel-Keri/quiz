package com.quiz.quiz.dto.question;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Base64;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class AllQuestionByThemeResponse {

    private UUID questionId;

    private UUID chosenAnswerId;

    private String text;

    private String image;

    private Double points;

    public AllQuestionByThemeResponse(UUID questionId, UUID chosenAnswerId, String text, String image, Double points) {

        this.questionId = questionId;
        this.chosenAnswerId = chosenAnswerId;
        this.text = text;
        this.image = image;
        this.points = points;
    }
}
