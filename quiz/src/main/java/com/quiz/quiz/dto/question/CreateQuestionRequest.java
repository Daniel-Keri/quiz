package com.quiz.quiz.dto.question;

import com.quiz.quiz.dto.answer.CreateAnswerRequest;
import com.quiz.quiz.entity.Answer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


@Data
@Accessors(chain = true)
public class CreateQuestionRequest {

    //private UUID id;

    private double score;

    private String theme;

    private List<CreateAnswerRequest> answers = new ArrayList<>();

    private String text;

    private String image;
}

