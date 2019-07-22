package com.quiz.quiz.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class QuestionScoreResponse {

    private UUID id;

    private String text;

    private String image;

    private double userScore;
}
