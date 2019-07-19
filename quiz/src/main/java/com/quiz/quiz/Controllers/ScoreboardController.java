package com.quiz.quiz.Controllers;

import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import com.quiz.quiz.exc.ScoreboardNotFoundException;
import com.quiz.quiz.services.ScoreboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("scoreboards")
@RequiredArgsConstructor
public class ScoreboardController {

    private final ScoreboardService scoreboardService;

    // GET
    @GetMapping(name = "/findByTheme")
    public ScoreboardResponse getScoreboardByTheme(@RequestParam(name = "theme") String theme) throws ScoreboardNotFoundException {

        return scoreboardService.getScoreboardByTheme(theme);
    }

    @GetMapping()
    public List<ScoreboardResponse> getScoreBoard() {

        return scoreboardService.getScoreBoard();
    }

}
