package com.quiz.quiz.dto.question;

import com.quiz.quiz.entity.Answer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class QuestionRequest {

    private UUID id;

    private double score;

    private String theme;

    private List<Answer> answers = new ArrayList<>();

    private String text;

}
