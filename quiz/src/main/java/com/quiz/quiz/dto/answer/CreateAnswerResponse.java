package com.quiz.quiz.dto.answer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class CreateAnswerResponse {

    private String text;

    private String image;

    private Boolean isCorrect;

    // private UUID id;
}
