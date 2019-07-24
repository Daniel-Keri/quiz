package com.quiz.quiz.dto.answer;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Base64;

@Data
@Accessors(chain=true)
public class QuestionAnswerResponse {

    private String text;

    private String image;

    private Boolean isCorrect;

    // private UUID id;
}
