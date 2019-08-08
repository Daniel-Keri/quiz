package com.quiz.quiz.services;

import com.quiz.quiz.converter.AnsweredQuestionConverter;
import com.quiz.quiz.dto.AnsweredQuestion.AnsweredQuestionRequest;
import com.quiz.quiz.dto.AnsweredQuestion.MyScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenGlobalScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenScoreByThemeResponse;
import com.quiz.quiz.entity.AnsweredQuestion;
import com.quiz.quiz.entity.entityColumnNames.EntityColumnNames.ANSWERED_QUESTION;
import com.quiz.quiz.entity.entityColumnNames.EntityColumnNames.TABLE;
import com.quiz.quiz.entity.entityColumnNames.EntityColumnNames.USER_ACCOUNT;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.principal.CustomPrincipal;
import com.quiz.quiz.repository.AnsweredQuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@RequiredArgsConstructor

@SqlResultSetMapping(
        name = "TopTenScoreByThemeResult",
        classes = {
                @ConstructorResult(
                        targetClass = TopTenScoreByThemeResponse.class,
                        columns = {
                                @ColumnResult(name = "score", type = Double.class),
                                @ColumnResult(name = "username", type = String.class)
                        })
        })
@NamedNativeQuery(
        name = "getTopTenScoreByTheme",
        query = "SELECT " +
                USER_ACCOUNT.ID + ", SUM(" + ANSWERED_QUESTION.POINTS + ") " +
                "FROM " +
                TABLE.ANSWERED_QUESTION + " INNER JOIN " + TABLE.USER_ACCOUNT + " " +
                "ON " +
                ANSWERED_QUESTION.USER_ACCOUNT_ID + " = " + USER_ACCOUNT.ID + " " +
                "WHERE " +
                ANSWERED_QUESTION.THEME + " = :theme " +
                "AND " +
                ANSWERED_QUESTION.IS_CORRECT + " IS TRUE " +
                "GROUP BY " +
                USER_ACCOUNT.ID + " " +
                "ORDER BY " +
                "SUM(" + ANSWERED_QUESTION.POINTS + ") DESC " +
                "LIMIT 10",
        resultSetMapping = "TopTenScoreByThemeResult")

public class AnsweredQuestionService {

    @Autowired
    private final EntityManagerFactory entityManagerFactory;

    private final AnsweredQuestionsRepository answeredQuestionsRepository;
    private final AnsweredQuestionConverter answeredQuestionConverter;
    private final CustomPrincipal customPrincipal;

    //POST
    public AnsweredQuestion createAnsweredQuestion(AnsweredQuestionRequest answeredQuestionRequest) {

        return answeredQuestionsRepository
                .save(answeredQuestionConverter.toAnsweredQuestion(customPrincipal.getLoggedInAccountId(), answeredQuestionRequest));
    }
    
    // GET
    public Page<TopTenGlobalScoreResponse> getTopTenGlobalScore(Pageable pageable) {

        String queryString =
//                "SELECT score, username " +
//                "FROM ( " +
//                    "SELECT SUM(aq.points) AS score, ua.username AS username " +
//                    "FROM AnsweredQuestion aq " +
//                    "INNER JOIN UserAccount ua " +
//                    "ON aq.userAccountId = ua.id " +
//                    "WHERE aq.isCorrect IS TRUE " +
//                    "GROUP BY ua.id " +
//                    "ORDER BY SUM(aq.points) DESC " +
//                " ) AS scores INNER JOIN (SELECT DISTINCT(score) AS distinct_score FROM scores LIMIT 10) ON score = distinct_score ";

                "SELECT score, username " +
                "FROM ( " +
                    "SELECT SUM(aq.points) AS score, ua.username AS username " +
                    "FROM AnsweredQuestion aq " +
                    "INNER JOIN UserAccount ua " +
                    "ON aq.userAccountId = ua.id " +
                    "WHERE aq.isCorrect IS TRUE " +
                    "GROUP BY ua.id " +
                    "ORDER BY SUM(aq.points) DESC) AS scores " +
                "WHERE score IN ( " +
                    "SELECT DISTINCT(score) " +
                    "FROM scores " +
                    "LIMIT 2) ";

        List<TopTenGlobalScoreResponse> result = entityManagerFactory.createEntityManager().createQuery("" +
                "SELECT new com.quiz.quiz.dto.AnsweredQuestion.TopTenGlobalScoreResponse(SUM(aq.points), ua.username) " +
                "FROM AnsweredQuestion aq " +
                    "INNER JOIN UserAccount ua " +
                    "ON aq.userAccountId = ua.id " +
                "WHERE aq.isCorrect IS TRUE " +
                "GROUP BY ua.id " +
                "ORDER BY SUM(aq.points) DESC ", TopTenGlobalScoreResponse.class)
                .setMaxResults(10)
                .getResultList();

        return new PageImpl<>(result, pageable, result.size());
    }

    public Page<TopTenScoreByThemeResponse> getTopTenScoreByTheme(String theme, Pageable pageable) throws EntityNotFoundException {

        String sqlString =
//                "SELECT " +
////                        ANSWERED_QUESTION.POINTS + " AS score, " + USER_ACCOUNT.USERNAME + " AS username " +
//                        " score, username " +
//                "FROM ( " +
//                        "SELECT SUM(" + ANSWERED_QUESTION.POINTS + ") AS score, " + USER_ACCOUNT.USERNAME + " AS username " +
//                        "FROM " + TABLE.ANSWERED_QUESTION + " " +
//                        "INNER JOIN " + TABLE.USER_ACCOUNT + " " +
//                        "ON " + ANSWERED_QUESTION.ID + " = " + USER_ACCOUNT.ID + " " +
//                        "WHERE " + ANSWERED_QUESTION.IS_CORRECT + " IS TRUE AND " +
//                        ANSWERED_QUESTION.THEME + " = :theme " +
//                        "GROUP BY " + USER_ACCOUNT.ID + " " +
//                        //"ORDER BY SUM(aq.points) DESC " +
//                        "ORDER BY score DESC) AS scores " +
//                "INNER JOIN LATERAL ( " +
//                        "SELECT DISTINCT(score) AS distinct_score " +
//                        "FROM scores " +
//                        "LIMIT 2) AS distinct_scores " +
//                "ON score = distinct_score ";

                "SELECT " +
//                        ANSWERED_QUESTION.POINTS + " AS score, " + USER_ACCOUNT.USERNAME + " AS username " +
                    " score, username " +
                    "FROM ( " +
                        "SELECT SUM(" + ANSWERED_QUESTION.POINTS + ") AS score, " + USER_ACCOUNT.USERNAME + " AS username " +
                        "FROM " + TABLE.ANSWERED_QUESTION + " " +
                        "INNER JOIN " + TABLE.USER_ACCOUNT + " " +
                        "ON " + ANSWERED_QUESTION.ID + " = " + USER_ACCOUNT.ID + " " +
                        "WHERE " + ANSWERED_QUESTION.IS_CORRECT + " IS TRUE AND " +
                        ANSWERED_QUESTION.THEME + " = :theme " +
                        "GROUP BY " + USER_ACCOUNT.ID + " " +
                        //"ORDER BY SUM(aq.points) DESC " +
                        "ORDER BY score DESC) AS scores " +
                    "WHERE score IN ( " +
                        "SELECT DISTINCT(score) " + "AS distinct_score " +
                        "FROM scores " +
                        "LIMIT 2) ";

        String resultSetMapping = "TopTenScoreByThemeResult";
        Query queryResult = entityManagerFactory.createEntityManager()
                .createNativeQuery(sqlString, resultSetMapping)
                .setParameter("theme", theme);
        List<TopTenScoreByThemeResponse> topTenGlobalScoreResponses = queryResult.getResultList();

//        List<TopTenScoreByThemeResponse> topTenGlobalScoreResponses;

        try {
//            topTenGlobalScoreResponses = queryResult;
            if (topTenGlobalScoreResponses.isEmpty()) {
                throw new EntityNotFoundException();
            }

            return new PageImpl<>(topTenGlobalScoreResponses, pageable, topTenGlobalScoreResponses.size());
        } catch (ClassCastException exc) {
            throw new ClassCastException(exc.getMessage());
        }
    }

    public Page<MyScoreResponse> getMyScores(Pageable pageable) {

        return answeredQuestionsRepository.getMyScores(customPrincipal.getLoggedInAccountId(), pageable);
    }
}
