package com.quiz.quiz.repository;

import com.quiz.quiz.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    Optional<Question> findByTheme(String theme);

    Optional<Question> findByPoints(Double points);

    Page<Question> findAllByTheme(String theme, Pageable pageable);

    @Query("SELECT q.theme FROM Question q GROUP BY q.theme ORDER BY q.theme")
    List<String> findAllTheme();

//    @Query("SELECT new com.quiz.quiz.dto.question.QuestionScoreResponse()")
//    Page<Question> findAll();

    //Page<Question> findAll(Pageable pageable);
/*
    @Query("SELECT q FROM Question q WHERE q NOT IN :answeredQuestions ORDER BY RANDOM() LIMIT :count")
    List<Question> getRandomQuestions(@Param(value = "count") int count,
                                      @Param(value = "answeredQuestions") Map<UUID, Question> answeredQuestions);
*/
}
