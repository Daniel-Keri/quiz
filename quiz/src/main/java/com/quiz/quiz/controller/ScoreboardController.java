package com.quiz.quiz.controller;

import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import com.quiz.quiz.exc.ScoreboardNotFoundException;
import com.quiz.quiz.services.ScoreboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("scoreboards")
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
