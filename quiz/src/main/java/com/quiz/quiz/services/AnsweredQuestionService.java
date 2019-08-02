package com.quiz.quiz.services;

import com.quiz.quiz.converter.AnsweredQuestionConverter;
import com.quiz.quiz.dto.AnsweredQuestion.AnsweredQuestionRequest;
import com.quiz.quiz.entity.AnsweredQuestion;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.repository.AnsweredQuestionsRepository;
import com.quiz.quiz.repository.accounts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnsweredQuestionService {

    private final AnsweredQuestionConverter answeredQuestionConverter;
    private final AnsweredQuestionsRepository answeredQuestionsRepository;
    private final AccountRepository accountRepository;

    //POST
    public AnsweredQuestion createAnsweredQuestion(AnsweredQuestionRequest answeredQuestionRequest) {

        return answeredQuestionsRepository.save(
                answeredQuestionConverter.toAnsweredQuestion(
                        accountRepository.getCurrentAccountId().orElseThrow(EntityNotFoundException::new),
                        answeredQuestionRequest));
    }
}
