package com.quiz.quiz.repository;

import com.quiz.quiz.dto.AnsweredQuestion.MyScoreResponse;
import com.quiz.quiz.entity.AnsweredQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AnsweredQuestionsRepository extends JpaRepository<AnsweredQuestion, UUID> {

    @Query("SELECT new com.quiz.quiz.dto.AnsweredQuestion.MyScoreResponse(aq.theme, SUM(aq.points)) " +
            "FROM AnsweredQuestion aq " +
            "WHERE aq.userAccountId = ?#{principal.id} " +
            "GROUP BY aq.theme " +
            "ORDER BY SUM(aq.points) DESC ")
    Page<MyScoreResponse> getMyScores(Pageable pageable);
}
