package com.quiz.quiz.services;

import com.quiz.quiz.converter.ScoreboardConverter;
import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import com.quiz.quiz.exceptions.ScoreboardNotFoundException;
import com.quiz.quiz.repository.ScoreboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreboardService {

    private final ScoreboardRepository scoreboardRepository;
    private final ScoreboardConverter scoreboardConverter;

    // GET
    public List<ScoreboardResponse> getScoreboardByTheme(String theme) throws ScoreboardNotFoundException {

        return scoreboardRepository.getScoreboardByTheme(theme).stream()
                .map(scoreboardConverter::toScoreboardResponse)
                .collect(Collectors.toList());
    }

    public List<ScoreboardResponse> getScoreBoard() {

        return scoreboardRepository.findAllOrderedByThemeThenScore().stream()
                .map(scoreboardConverter::toScoreboardResponse)
                .collect(Collectors.toList());
    }

    public List<ScoreboardResponse> getScoreBoard2() {

        return scoreboardRepository.findAllOrderedByThemeThenScore()
                .stream()
                .map(scoreboardConverter::toScoreboardResponse)
                .collect(Collectors.toList());
    }
}