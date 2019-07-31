package com.quiz.quiz.repository;

import com.quiz.quiz.dto.question.AllQuestionByThemeResponse;
import com.quiz.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    Optional<Question> findByTheme(String theme);

    Optional<Question> findByPoints(Double points);

//    @OrderBy("Question.id")
//    Page<Question> findAllByTheme(List<String> theme, Pageable pageable);

    List<Question> findAllByThemeIn(List<String> theme);


    @Query("SELECT q.theme FROM Question q GROUP BY q.theme ORDER BY q.theme")
    List<String> findAllTheme();

    @Query("SELECT new com.quiz.quiz.dto.question.AllQuestionByThemeResponse(q.id, aq.chosenAnswerId, q.text, q.image) " +
            "FROM com.quiz.quiz.entity.Question q LEFT JOIN AnsweredQuestion aq " +
            "ON q.id = aq.questionId AND (" +
            //"aq.userAccountId IS NULL OR " +
            "aq.userAccountId = ?#{principal.id})" +
            "WHERE q.theme = ?1 " +
            "ORDER BY aq.chosenAnswerId DESC")
    List<AllQuestionByThemeResponse> getAllQuestionsByThemeResponses(String theme);

    @Modifying
    @Query("DELETE FROM Question q WHERE q.theme IN ?1")
    void deleteQuestionsByThemes(List<String> themes);
}
