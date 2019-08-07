package com.quiz.quiz.services;

import com.quiz.quiz.converter.AnsweredQuestionConverter;
import com.quiz.quiz.dto.AnsweredQuestion.AnsweredQuestionRequest;
import com.quiz.quiz.dto.AnsweredQuestion.MyScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenGlobalScoreResponse;
import com.quiz.quiz.dto.AnsweredQuestion.TopTenScoreByThemeResponse;
import com.quiz.quiz.entity.AnsweredQuestion;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.repository.AnsweredQuestionsRepository;
import com.quiz.quiz.security.CustomUserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnsweredQuestionService {

    @Autowired
    private final EntityManagerFactory entityManagerFactory;

    private final AnsweredQuestionsRepository answeredQuestionsRepository;
    private final AnsweredQuestionConverter answeredQuestionConverter;

    //POST
    public AnsweredQuestion createAnsweredQuestion(AnsweredQuestionRequest answeredQuestionRequest) {

        return answeredQuestionsRepository
                .save(answeredQuestionConverter.toAnsweredQuestion(getLoggedInAccountId(), answeredQuestionRequest));
    }
    
    // GET
    public Page<TopTenGlobalScoreResponse> getTopTenGlobalScore(Pageable pageable) {

        List<TopTenGlobalScoreResponse> result = entityManagerFactory.createEntityManager().createQuery("" +
                "SELECT new com.quiz.quiz.dto.AnsweredQuestion.TopTenGlobalScoreResponse(ua.username, SUM(aq.points)) " +
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

        List<TopTenScoreByThemeResponse> result = entityManagerFactory.createEntityManager().createQuery("" +
                "SELECT new com.quiz.quiz.dto.AnsweredQuestion.TopTenScoreByThemeResponse(ua.username, SUM(aq.points)) " +
                "FROM AnsweredQuestion aq " +
                    "INNER JOIN UserAccount ua " +
                    "ON aq.userAccountId = ua.id " +
                "WHERE aq.theme = :theme AND aq.isCorrect IS TRUE " +
                "GROUP BY ua.id " +
                "ORDER BY SUM(aq.points) DESC ", TopTenScoreByThemeResponse.class)
                .setParameter("theme", theme)
                .setMaxResults(10)
                .getResultList();

        if (result.isEmpty()) {throw new EntityNotFoundException();}

        return new PageImpl<>(result, pageable, result.size());
    }

    public Page<MyScoreResponse> getMyScores(Pageable pageable) {

        return answeredQuestionsRepository.getMyScores(getLoggedInAccountId(), pageable);
    }

    private UUID getLoggedInAccountId() {

        return ((CustomUserImpl) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal())
                .getId();
    }
}
