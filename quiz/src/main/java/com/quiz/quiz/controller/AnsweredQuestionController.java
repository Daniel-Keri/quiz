package com.quiz.quiz.controller;

import com.quiz.quiz.dto.AnsweredQuestion.AnsweredQuestionRequest;
import com.quiz.quiz.dto.AnsweredQuestion.MyScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenGlobalScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenScoreByThemeResponse;
import com.quiz.quiz.entity.AnsweredQuestion;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.services.AnsweredQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.quiz.quiz.config.constants.URIConstants.QUESTION;

@RestController
@RequestMapping(QUESTION)
@RequiredArgsConstructor
public class AnsweredQuestionController {

    private final AnsweredQuestionService answeredQuestionService;

    //POST
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/chooseAnswer")
    public AnsweredQuestion createAnsweredQuestion(@Validated @RequestBody AnsweredQuestionRequest answeredQuestionRequest){

        return answeredQuestionService.createAnsweredQuestion(answeredQuestionRequest);
    }

    // GET
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/scores/topTen")
    @ResponseBody
    public Page<TopTenGlobalScoreResponse> getTopTenGlobalScore(Pageable pageable) {

        return answeredQuestionService.getTopTenGlobalScore(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/scores/topTen/byTheme/{theme}")
    @ResponseBody
    public Page<TopTenScoreByThemeResponse> getTopTenScoreByTheme(@PathVariable("theme") String theme, Pageable pageable) throws EntityNotFoundException {

        return answeredQuestionService.getTopTenScoreByTheme(theme, pageable);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/scores/myScores")
    @ResponseBody
    public Page<MyScoreResponse> getMyScores(Pageable pageable) {

        return answeredQuestionService.getMyScores(pageable);
    }
}
