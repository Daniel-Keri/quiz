package com.quiz.quiz.repository;

import com.quiz.quiz.dto.QuestionScoreResponse;
import com.quiz.quiz.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> findByTheme(String theme);

    Optional<Question> findByScore(Double score);

    Page<Question> findAllByTheme(String theme, Pageable pageable);


//    @Query("SELECT new com.quiz.quiz.dto.QuestionScoreResponse()")
//    Page<Question> findAll();


    //Page<Question> findAll(Pageable pageable);
/*
    @Query("SELECT q FROM Question q WHERE q NOT IN :answeredQuestions ORDER BY RANDOM() LIMIT :count")
    List<Question> getRandomQuestions(@Param(value = "count") int count,
                                      @Param(value = "answeredQuestions") Map<UUID, Question> answeredQuestions);
*/
}
