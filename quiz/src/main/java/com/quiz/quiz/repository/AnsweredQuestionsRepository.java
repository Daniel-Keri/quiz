package com.quiz.quiz.repository;

import com.quiz.quiz.entity.AnsweredQuestion;
import com.quiz.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnsweredQuestionsRepository extends JpaRepository<AnsweredQuestion, UUID> {

    @Query("SELECT aq FROM AnsweredQuestion aq WHERE aq.userAccountId = ?#{principal.id}")
    List<AnsweredQuestion> findAllAnsweredQuestions();
}
