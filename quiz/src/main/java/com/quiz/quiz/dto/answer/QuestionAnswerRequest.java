package com.quiz.quiz.dto.answer;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain=true)
public class QuestionAnswerRequest {

    private UUID id;
}
