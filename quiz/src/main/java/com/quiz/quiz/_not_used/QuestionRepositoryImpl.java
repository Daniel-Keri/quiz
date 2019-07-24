package com.quiz.quiz._not_used;

import com.quiz.quiz.entity.Question;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class QuestionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    //@Override
    public List<Question> getRandomQuestions(int count,
                                             @Param(value = "answeredQuestions") Map<UUID, Question> answeredQuestions)
    {
        return entityManager.createQuery("SELECT q FROM Question q WHERE q NOT IN :answeredQuestions ORDER BY RANDOM()",
                Question.class).setMaxResults(count).getResultList();
    }
}