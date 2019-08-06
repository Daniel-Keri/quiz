package com.quiz.quiz.repository;

import com.quiz.quiz.dto.question.AllQuestionByThemeResponse;
import com.quiz.quiz.entity.Answer;
import com.quiz.quiz.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface  QuestionRepository extends JpaRepository<Question, UUID> {

    Optional<Question> findByTheme(String theme);

    Optional<Question> findByPoints(Double points);

//    @OrderBy("Question.id")
//    Page<Question> findAllByTheme(List<String> theme, Pageable pageable);

    @Query("SELECT q FROM Question q JOIN FETCH q.answers " +
            "WHERE q.id = :id")
    Optional<Question> findByIdEager(@Param(value = "id") UUID id);

    @Query("SELECT q FROM Question q JOIN FETCH q.answers " +
            "WHERE q.id IN :ids")
    List<Question> findAllByIdIn(@Param(value = "ids") List<UUID> ids);

    @Query("SELECT q FROM Question q JOIN FETCH q.answers " +
            "WHERE q.theme IN :themes")
    List<Question> findAllByThemeIn(@Param(value = "themes") List<String> themes);

    @Query("SELECT q.theme FROM Question q GROUP BY q.theme ORDER BY q.theme")
    List<String> findAllTheme();

    @Query("SELECT q.answers FROM Question q WHERE q.id = ?1 ORDER BY q.theme")
    List<Answer> findAllQuestionAnswersById(UUID questionId);

    @Query("SELECT new com.quiz.quiz.dto.question.AllQuestionByThemeResponse(q.id, aq.chosenAnswerId, q.text, q.image, q.points) " +
            "FROM com.quiz.quiz.entity.Question q LEFT JOIN AnsweredQuestion aq " +
            "ON q.id = aq.questionId AND (" +
            //"aq.userAccountId IS NULL OR " +
            "aq.userAccountId = ?#{principal.id})" +
            "WHERE q.theme = ?1 " +
            "ORDER BY aq.chosenAnswerId DESC")
    List<AllQuestionByThemeResponse> getAllQuestionsByThemeResponses(String theme);

    @Query("SELECT new com.quiz.quiz.dto.question.AllQuestionByThemeResponse(q.id, aq.chosenAnswerId, q.text, q.image, q.points) " +
            "FROM com.quiz.quiz.entity.Question q LEFT JOIN AnsweredQuestion aq " +
            "ON q.id = aq.questionId AND (" +
            "aq.userAccountId = ?#{principal.id})" +
            "WHERE q.theme = ?1 " +
            "ORDER BY aq.chosenAnswerId DESC")
    Page<AllQuestionByThemeResponse> getAllQuestionsByThemeResponses(String theme, Pageable pageable);
}
