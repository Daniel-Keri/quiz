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

//@SqlResultSetMapping(
//        name = "TopTenScoreByThemeResult",
//        classes = {
//                @ConstructorResult(
//                        targetClass = TopTenScoreByThemeResponse.class,
//                        columns = {
//                                @ColumnResult(name = "score", type = Double.class),
//                                @ColumnResult(name = "username", type = String.class)
//                        })
//        })
//@NamedNativeQuery(
//        name = "getTopTenScoreByTheme",
//        query = "SELECT " +
//                USER_ACCOUNT.ID + ", SUM(" + ANSWERED_QUESTION.POINTS + ") " +
//                "FROM " +
//                TABLE.ANSWERED_QUESTION + " INNER JOIN " + TABLE.USER_ACCOUNT + " " +
//                "ON " +
//                ANSWERED_QUESTION.USER_ACCOUNT_ID + " = " + USER_ACCOUNT.ID + " " +
//                "WHERE " +
//                ANSWERED_QUESTION.THEME + " = :theme " +
//                "AND " +
//                ANSWERED_QUESTION.IS_CORRECT + " IS TRUE " +
//                "GROUP BY " +
//                USER_ACCOUNT.ID + " " +
//                "ORDER BY " +
//                "SUM(" + ANSWERED_QUESTION.POINTS + ") DESC " +
//                "LIMIT 10",
//        resultSetMapping = "TopTenScoreByThemeResult")

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

        String sqlString =
                "WITH scores AS (" +
                    "SELECT " +
                        "SUM(" + ANSWERED_QUESTION.POINTS + ") AS score, " +
                        USER_ACCOUNT.USERNAME + " AS username, " +
                        ANSWERED_QUESTION.USER_ACCOUNT_ID + " AS user_account_id " +
                    "FROM " + TABLE.ANSWERED_QUESTION + " " +
                    "INNER JOIN " + TABLE.USER_ACCOUNT + " " +
                    "ON " + ANSWERED_QUESTION.USER_ACCOUNT_ID + " = " + USER_ACCOUNT.ID + " " +
                    "WHERE " + ANSWERED_QUESTION.IS_CORRECT + " IS TRUE " +
                    "GROUP BY user_account_id, username " +
                    "ORDER BY score DESC) " +

                "SELECT score, username, user_account_id " +
                "FROM scores " +
                "INNER JOIN ( " +
                    "SELECT score AS distinct_score " +
                    "FROM scores " +
                    "GROUP BY distinct_score " +
                    "ORDER BY distinct_score DESC " +
                    "LIMIT 10) AS distinct_scores " +
                "ON scores.score = distinct_score ";

        String resultSetMapping = "TopTenScoreByThemeResult";

        Query queryResult = entityManagerFactory.createEntityManager()
                .createNativeQuery(sqlString, resultSetMapping);

        List<TopTenGlobalScoreResponse> topTenGlobalScoreResponses = queryResult.getResultList();

        return new PageImpl<>(topTenGlobalScoreResponses, pageable, topTenGlobalScoreResponses.size());
    }

    public Page<TopTenGlobalScoreResponse> getTopTenGlobalScore(Integer top, Pageable pageable) {

        String sqlString =
                "WITH scores AS (" +
                    "SELECT " +
                        "SUM(" + ANSWERED_QUESTION.POINTS + ") AS score, " +
                        USER_ACCOUNT.USERNAME + " AS username, " +
                        ANSWERED_QUESTION.USER_ACCOUNT_ID + " AS user_account_id " +
                    "FROM " + TABLE.ANSWERED_QUESTION + " " +
                    "INNER JOIN " + TABLE.USER_ACCOUNT + " " +
                    "ON " + ANSWERED_QUESTION.USER_ACCOUNT_ID + " = " + USER_ACCOUNT.ID + " " +
                    "WHERE " + ANSWERED_QUESTION.IS_CORRECT + " IS TRUE " +
                    "GROUP BY user_account_id, username " +
                    "ORDER BY score DESC) " +

                "SELECT score, username, user_account_id " +
                "FROM scores " +
                "INNER JOIN ( " +
                    "SELECT score AS distinct_score " +
                    "FROM scores " +
                    "GROUP BY distinct_score " +
                    "ORDER BY distinct_score DESC " +
                    "LIMIT :top) AS distinct_scores " +
                "ON scores.score = distinct_score ";

        String resultSetMapping = "TopTenScoreByThemeResult";

        Query queryResult = entityManagerFactory.createEntityManager()
                .createNativeQuery(sqlString, resultSetMapping)
                .setParameter("top", top);

        List<TopTenGlobalScoreResponse> topTenGlobalScoreResponses = queryResult.getResultList();

        return new PageImpl<>(topTenGlobalScoreResponses, pageable, topTenGlobalScoreResponses.size());
    }

    public Page<TopTenScoreByThemeResponse> getTopTenScoreByTheme(String theme, Pageable pageable) throws EntityNotFoundException {

        String sqlString =
                "WITH scores AS (" +
                    "SELECT " +
                        "SUM(" + ANSWERED_QUESTION.POINTS + ") AS score, " +
                        USER_ACCOUNT.USERNAME + " AS username, " +
                        ANSWERED_QUESTION.USER_ACCOUNT_ID + " AS user_account_id " +
                    "FROM " + TABLE.ANSWERED_QUESTION + " " +
                    "INNER JOIN " + TABLE.USER_ACCOUNT + " " +
                    "ON " + ANSWERED_QUESTION.USER_ACCOUNT_ID + " = " + USER_ACCOUNT.ID + " " +
                    "WHERE " + ANSWERED_QUESTION.IS_CORRECT + " IS TRUE " +
                    "AND " + ANSWERED_QUESTION.THEME + " = :theme " +
                    "GROUP BY user_account_id, username " +
                    "ORDER BY score DESC) " +

                "SELECT score, username, user_account_id " +
                "FROM scores " +
                "INNER JOIN ( " +
                    "SELECT score AS distinct_score " +
                    "FROM scores " +
                    "GROUP BY distinct_score " +
                    "ORDER BY distinct_score DESC " +
                    "LIMIT 10) AS distinct_scores " +
                "ON scores.score = distinct_score ";

        String resultSetMapping = "TopTenScoreByThemeResult";

        Query queryResult = entityManagerFactory.createEntityManager()
                .createNativeQuery(sqlString, resultSetMapping)
                .setParameter("theme", theme);

        List<TopTenScoreByThemeResponse> topTenGlobalScoreResponses = queryResult.getResultList();

        return new PageImpl<>(topTenGlobalScoreResponses, pageable, topTenGlobalScoreResponses.size());
    }

    public Page<TopTenScoreByThemeResponse> getTopTenScoreByTheme(String theme, Integer top, Pageable pageable) throws EntityNotFoundException {

        String sqlString =
                "WITH scores AS (" +
                    "SELECT " +
                        "SUM(" + ANSWERED_QUESTION.POINTS + ") AS score, " +
                        USER_ACCOUNT.USERNAME + " AS username, " +
                        ANSWERED_QUESTION.USER_ACCOUNT_ID + " AS user_account_id " +
                    "FROM " + TABLE.ANSWERED_QUESTION + " " +
                    "INNER JOIN " + TABLE.USER_ACCOUNT + " " +
                    "ON " + ANSWERED_QUESTION.USER_ACCOUNT_ID + " = " + USER_ACCOUNT.ID + " " +
                    "WHERE " + ANSWERED_QUESTION.IS_CORRECT + " IS TRUE " +
                    "AND " + ANSWERED_QUESTION.THEME + " = :theme " +
                    "GROUP BY user_account_id, username " +
                    "ORDER BY score DESC) " +

                "SELECT score, username, user_account_id " +
                "FROM scores " +
                "INNER JOIN ( " +
                    "SELECT score AS distinct_score " +
                    "FROM scores " +
                    "GROUP BY distinct_score " +
                    "ORDER BY distinct_score DESC " +
                    "LIMIT :top) AS distinct_scores " +
                "ON scores.score = distinct_score ";

        String resultSetMapping = "TopTenScoreByThemeResult";

        Query queryResult = entityManagerFactory.createEntityManager()
                .createNativeQuery(sqlString, resultSetMapping)
                .setParameter("theme", theme)
                .setParameter("top", top);

        List<TopTenScoreByThemeResponse> topTenGlobalScoreResponses = queryResult.getResultList();

        return new PageImpl<>(topTenGlobalScoreResponses, pageable, topTenGlobalScoreResponses.size());
    }

    public Page<MyScoreResponse> getMyScores(Pageable pageable) {

        return answeredQuestionsRepository.getMyScores(customPrincipal.getLoggedInAccountId(), pageable);
    }
}
