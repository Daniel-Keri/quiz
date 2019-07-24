package com.quiz.quiz.controller;

import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import com.quiz.quiz.exceptions.ScoreboardNotFoundException;
import com.quiz.quiz.services.ScoreboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.quiz.quiz.config.constants.URIConstants.SCOREBOARD;


@RestController
@RequestMapping(SCOREBOARD)
@RequiredArgsConstructor
public class ScoreboardController {

    private final ScoreboardService scoreboardService;

    // GET
    @GetMapping("/findByTheme")
    @ResponseBody
    public List<ScoreboardResponse> getScoreboardByTheme(@RequestParam(name = "theme") String theme) throws ScoreboardNotFoundException {

        return scoreboardService.getScoreboardByTheme(theme);
    }

    @GetMapping
    @ResponseBody
    public List<ScoreboardResponse> getScoreBoard() {

        return scoreboardService.getScoreBoard();
    }

}
