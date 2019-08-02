package com.quiz.quiz.controller;

import com.quiz.quiz.dto.AnsweredQuestion.AnsweredQuestionRequest;
import com.quiz.quiz.entity.AnsweredQuestion;
import com.quiz.quiz.services.AnsweredQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.quiz.quiz.config.constants.URIConstants.ANSWERED_QUESTION;

@RestController
@RequestMapping(ANSWERED_QUESTION)
@RequiredArgsConstructor
public class AnsweredQuestionController {
    private final AnsweredQuestionService answeredQuestionService;
    //POST
    @PostMapping
    public AnsweredQuestion createAnsweredQuestion(@Validated @RequestBody AnsweredQuestionRequest answeredQuestionRequest){

        return answeredQuestionService.createAnsweredQuestion(answeredQuestionRequest);
    }


}
