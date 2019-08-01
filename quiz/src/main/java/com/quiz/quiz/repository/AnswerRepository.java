package com.quiz.quiz.repository;

import com.quiz.quiz.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    @Query("SELECT a FROM Answer a JOIN FETCH a.question q WHERE q.id = ?1")
    //@Query("SELECT a FROM Answer a INNER JOIN Question q ON a.question.id = q.id WHERE q.id = ?1")
    //@Query("SELECT a, a.question FROM Answer a INNER JOIN Question q ON a.question.id = q.id WHERE q.id = ?1")
    List<Answer> findAllQuestionAnswersById(UUID questionId);
}
