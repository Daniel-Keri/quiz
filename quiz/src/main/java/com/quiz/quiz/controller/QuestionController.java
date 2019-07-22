package com.quiz.quiz.controller;


import com.quiz.quiz.dto.*;
import com.quiz.quiz.entity.Answer;
import com.quiz.quiz.entity.Question;
import com.quiz.quiz.exceptions.QuestionNotFoundException;
import com.quiz.quiz.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @DeleteMapping
    public void deleteQuestion(UUID id) throws QuestionNotFoundException {
        questionService.deleteQuestion(id);
    }

    @DeleteMapping("/deleteQuestions")
    public void deleteQuestions(List<UUID> ids) throws QuestionNotFoundException {
        questionService.deleteQuestions(ids);
    }

    @GetMapping
    public Page<QuestionScoreResponse> findAll(Pageable pageable) {

        return questionService.findAll(pageable);
    }

    @GetMapping("/random")
    public Page<QuestionScoreResponse> findAllRandomized(Pageable pageable) {

        return questionService.findAllRandomized(pageable);
    }

    @GetMapping("/answers")
    List<Answer> findAllQuestionAnswers(UUID questionId) {

        return questionService.findAllQuestionAnswers(questionId);
    }

    // POST
    @PostMapping
    public CreateQuestionResponse createQuestion(@RequestBody CreateQuestionRequest createQuestionRequest){

        return questionService.createQuestion(createQuestionRequest);
    }
}


