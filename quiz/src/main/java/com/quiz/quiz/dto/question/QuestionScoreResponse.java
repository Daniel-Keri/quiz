package com.quiz.quiz.dto.question;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Base64;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class QuestionScoreResponse {

    private UUID id;

    private String text;

    private String image;

    private UUID chosenAnswerId;
}
