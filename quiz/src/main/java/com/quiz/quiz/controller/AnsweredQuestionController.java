package com.quiz.quiz.controller;

import com.quiz.quiz.dto.AnsweredQuestion.MyScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenGlobalScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenScoreByThemeResponse;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.services.AnsweredQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.quiz.quiz.config.constants.URIConstants.ANSWERED_QUESTION;


@RestController
@RequestMapping(ANSWERED_QUESTION)
@RequiredArgsConstructor
public class AnsweredQuestionController {

    private final AnsweredQuestionService answeredQuestionService;

    // GET
    @GetMapping("/topTen")
    @ResponseBody
    public Page<TopTenGlobalScoreResponse> getTopTenGlobalScore(Pageable pageable) {

        return answeredQuestionService.getTopTenGlobalScore(pageable);
    }

    @GetMapping("/topTen/byTheme/{theme}")
    @ResponseBody
    public Page<TopTenScoreByThemeResponse> getTopTenScoreByTheme(@PathVariable("theme") String theme, Pageable pageable) throws EntityNotFoundException {

        return answeredQuestionService.getTopTenScoreByTheme(theme, pageable);
    }

    @GetMapping("/myScores")
    @ResponseBody
    public Page<MyScoreResponse> getMyScores(Pageable pageable) {

        return answeredQuestionService.getMyScores(pageable);
    }
}
