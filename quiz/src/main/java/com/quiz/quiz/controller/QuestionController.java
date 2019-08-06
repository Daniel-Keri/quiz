package com.quiz.quiz.controller;


import com.quiz.quiz.dto.answer.QuestionAnswerResponse;
import com.quiz.quiz.dto.question.AllQuestionByThemeResponse;
import com.quiz.quiz.dto.question.CreateQuestionRequest;
import com.quiz.quiz.dto.question.CreateQuestionResponse;
import com.quiz.quiz.dto.question.ThemeResponse;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.services.QuestionService;
import com.quiz.quiz.validation.requestValidators.CreateQuestionRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.quiz.quiz.config.constants.URIConstants.QUESTION;

@RestController
@RequestMapping(QUESTION)
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final CreateQuestionRequestValidator createQuestionRequestValidator;

    @InitBinder("createQuestionRequest")
    protected void initCreateQuestionRequestValidatorBinder(WebDataBinder binder) {
        binder.addValidators(createQuestionRequestValidator);
    }

    // POST
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CreateQuestionResponse createQuestion(@Validated @RequestBody CreateQuestionRequest createQuestionRequest){

        return questionService.createQuestion(createQuestionRequest);
    }

    // GET
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/themes")
    public Page<ThemeResponse> findAllTheme(Pageable pageable) {

        return questionService.findAllTheme(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/byTheme/{theme}")
    public Page<AllQuestionByThemeResponse> findAllByTheme(@PathVariable("theme") String theme, Pageable pageable) {

        return questionService.findAllByTheme(theme, pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/byThemeRandomized/{theme}")
    public Page<AllQuestionByThemeResponse> findAllByThemeRandomized(@PathVariable("theme") String theme, Pageable pageable) {

        return questionService.findAllByThemeRandomized(theme, pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/answers")
    Page<QuestionAnswerResponse> findAllQuestionAnswers(@RequestParam(name = "id") UUID id, Pageable pageable) throws EntityNotFoundException {

        return questionService.findAllQuestionAnswers(id,pageable);
    }

    // DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteQuestion")
    public void deleteQuestion(@RequestParam(name = "id") UUID id) throws EntityNotFoundException {
        questionService.deleteQuestion(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteQuestions")
    public void deleteQuestions(@RequestParam(name = "id") List<UUID> ids) throws EntityNotFoundException {
        questionService.deleteQuestions(ids);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteQuestionsByThemes")
    public void deleteQuestionsByThemes(@RequestParam(name = "themes") List<String> themes) throws EntityNotFoundException {
        questionService.deleteQuestionsByThemes(themes);
    }
}


