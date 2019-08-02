package com.quiz.quiz.converter;

import com.quiz.quiz.dto.AnsweredQuestion.AnsweredQuestionRequest;
import com.quiz.quiz.entity.AnsweredQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnsweredQuestionConverter {

   public AnsweredQuestion toAnsweredQuestion(UUID userAccountId, AnsweredQuestionRequest answeredQuestionRequest){

        return new AnsweredQuestion()
                .setQuestionId(answeredQuestionRequest.getQuestionId())
                .setChosenAnswerId(answeredQuestionRequest.getAnswerId())
                .setIsCorrect(answeredQuestionRequest.getIsCorrect())
                .setPoints(answeredQuestionRequest.getPoints())
                .setTheme(answeredQuestionRequest.getTheme())
                .setUserAccountId(userAccountId);

    }
}
