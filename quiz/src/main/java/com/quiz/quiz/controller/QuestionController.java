package com.quiz.quiz.controller;


import com.quiz.quiz.dto.answer.QuestionAnswerRequest;
import com.quiz.quiz.dto.answer.QuestionAnswerResponse;
import com.quiz.quiz.dto.question.CreateQuestionRequest;
import com.quiz.quiz.dto.question.CreateQuestionResponse;
import com.quiz.quiz.dto.question.QuestionScoreResponse;
import com.quiz.quiz.exceptions.QuestionNotFoundException;
import com.quiz.quiz.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.quiz.quiz.config.constants.URIConstants.QUESTION;

@RestController
@RequestMapping(QUESTION)
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    // DELETE
    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteQuestion(UUID id) throws QuestionNotFoundException {
        questionService.deleteQuestion(id);
    }

    @DeleteMapping("/deleteQuestions")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteQuestions(List<UUID> ids) throws QuestionNotFoundException {
        questionService.deleteQuestions(ids);
    }

    // GET
    @GetMapping("/findAllByTheme/{theme}")
    public Page<QuestionScoreResponse> findAllByTheme(@PathVariable("theme") String theme, Pageable pageable) {

        return questionService.findAllByTheme(theme, pageable);
    }

    @GetMapping("/findAllByThemeRandomized/{theme}")
    public Page<QuestionScoreResponse> findAllByThemeRandomized(@PathVariable("theme") String theme, Pageable pageable) {

        return questionService.findAllByThemeRandomized(theme, pageable);
    }

    @GetMapping("/answers")
    List<QuestionAnswerResponse> findAllQuestionAnswers(QuestionAnswerRequest questionAnswerRequest) {

        return questionService.findAllQuestionAnswers(questionAnswerRequest);
    }

    // POST
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CreateQuestionResponse createQuestion(@RequestBody CreateQuestionRequest createQuestionRequest){

        return questionService.createQuestion(createQuestionRequest);
    }
}


