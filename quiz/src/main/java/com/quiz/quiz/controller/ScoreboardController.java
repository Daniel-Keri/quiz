package com.quiz.quiz.controller;

import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.services.ScoreboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.quiz.quiz.config.constants.URIConstants.SCOREBOARD;


@RestController
@RequestMapping(SCOREBOARD)
@RequiredArgsConstructor
public class ScoreboardController {

    private final ScoreboardService scoreboardService;

    // GET
    @GetMapping("/byTheme/{theme}")
    @ResponseBody
    public Page<ScoreboardResponse> getScoreboardByTheme(@PathVariable("theme") String theme, Pageable pageable) throws EntityNotFoundException {

        return scoreboardService.getScoreboardByTheme(theme, pageable);
    }

    @GetMapping
    @ResponseBody
    public Page<ScoreboardResponse> getScoreBoard(Pageable pageable) {

        return scoreboardService.getScoreBoard(pageable);
    }
}
