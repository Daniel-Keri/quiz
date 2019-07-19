package com.quiz.quiz.repository;

import com.quiz.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> findByTheme(String theme);

    Optional<Question> findByScore(Double score);


}
