package com.quiz.quiz.services;

import com.quiz.quiz.converter.ScoreboardConverter;
import com.quiz.quiz.dto.scoreboard.ScoreboardResponse;
import com.quiz.quiz.exceptions.ScoreboardNotFoundException;
import com.quiz.quiz.repository.ScoreboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreboardService {

    private final ScoreboardRepository scoreboardRepository;
    private final ScoreboardConverter scoreboardConverter;

    // GET
    public Page<ScoreboardResponse> getScoreboardByTheme(String theme, Pageable pageable) throws ScoreboardNotFoundException {

        List<ScoreboardResponse> scoreboardResponses = scoreboardRepository.getScoreboardByTheme(theme).stream()
                .map(scoreboardConverter::toScoreboardResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(scoreboardResponses, pageable, scoreboardResponses.size());
    }

    public Page<ScoreboardResponse> getScoreBoard(Pageable pageable) {

        List<ScoreboardResponse> scoreboardResponses = scoreboardRepository.findAllOrderedByThemeThenScore().stream()
                .map(scoreboardConverter::toScoreboardResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(scoreboardResponses, pageable, scoreboardResponses.size());
    }
}