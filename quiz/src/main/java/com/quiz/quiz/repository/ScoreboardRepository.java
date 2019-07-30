package com.quiz.quiz.repository;

import com.quiz.quiz.entity.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ScoreboardRepository extends JpaRepository<Scoreboard, UUID> {

    @Query("SELECT s " +
            "FROM Scoreboard s " +
            "WHERE s.theme = :theme " +
            "ORDER BY s.score DESC")
    List<Scoreboard> getScoreboardByTheme(@Param(value = "theme") String theme);

    @Query("SELECT s " +
            "FROM Scoreboard s " +
            "ORDER BY s.theme, s.score DESC")
    List<Scoreboard> findAllOrderedByThemeThenScore();
}
