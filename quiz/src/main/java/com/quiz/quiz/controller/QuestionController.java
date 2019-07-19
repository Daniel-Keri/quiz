package com.quiz.quiz.controller;


import com.quiz.quiz.dto.QuestionRequest;
import com.quiz.quiz.dto.QuestionResponse;
import com.quiz.quiz.exceptions.QuestionNotFoundExcption;
import com.quiz.quiz.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public QuestionResponse createQuestion(@RequestBody QuestionRequest questionRequest)
    {
        return questionService.createQuestion(questionRequest);
    }

    @DeleteMapping
    public void deleteQuestion(UUID id) throws QuestionNotFoundExcption {
        questionService.deleteQuestion(id);
    }

    @DeleteMapping
    public void deleteQuestions(List<UUID> ids) throws QuestionNotFoundExcption {
        questionService.deleteQuestions(ids);
    }
}
    /*public
*/

