package com.quiz.quiz.dto.question;

import com.quiz.quiz.dto.answer.CreateAnswerResponse;
import com.quiz.quiz.entity.Answer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class CreateQuestionResponse {

    private UUID id;

    private String text;

    private String theme;

    private List<CreateAnswerResponse> answers = new ArrayList<>();

    private String image;
        
    private Double points;
}
