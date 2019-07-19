package com.quiz.quiz.services;

import com.quiz.quiz.converter.ScoreboardConverter;
import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import com.quiz.quiz.exc.ScoreboardNotFoundException;
import com.quiz.quiz.repository.ScoreboardRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreboardService {

    private final ScoreboardRepository scoreboardRepository;
    private final ScoreboardConverter scoreboardConverter;

    public ScoreboardResponse getScoreboardByTheme(String theme) throws ScoreboardNotFoundException {

        return scoreboardConverter.toScoreboardResponse(
                scoreboardRepository.getScoreboardByTheme(theme).orElseThrow(ScoreboardNotFoundException::new)
        );
    }

    public List<ScoreboardResponse> getScoreBoard() {

        return scoreboardRepository.findAll().stream()
                .map(scoreboardConverter::toScoreboardResponse)
                .collect(Collectors.toList());
    }
}
