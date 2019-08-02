package com.quiz.quiz.dto.AnsweredQuestion;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class AnsweredQuestionRequest {

    private UUID questionId;

    private UUID answerId;

    private Boolean isCorrect;

    private Double points;

    private String theme;
}
