package com.quiz.quiz.repository;

import com.quiz.quiz.Entity.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScoreboardRepository extends JpaRepository<Scoreboard, UUID> {

    @Query("SELECT s " +
            "FROM Scoreboard s " +
            "WHERE s.theme = :theme " +
            "GROUP BY s.theme " +
            "ORDER BY s.score DESC")
    List<Scoreboard> getScoreboardByTheme(@Param(value = "theme") String theme);
}
