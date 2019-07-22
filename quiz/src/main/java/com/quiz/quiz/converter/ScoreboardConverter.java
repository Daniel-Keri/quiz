package com.quiz.quiz.converter;

import com.quiz.quiz.entity.Scoreboard;
import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreboardConverter {

    public ScoreboardResponse toScoreboardResponse(Scoreboard scoreboard) {

        return new ScoreboardResponse()
                .setTheme(scoreboard.getTheme())
                .setScore(scoreboard.getScore())
                .setUsername(scoreboard.getUsername());
    }
}
