package com.quiz.quiz.converter;

import com.quiz.quiz.dto.answer.CreateAnswerRequest;
import com.quiz.quiz.dto.answer.CreateAnswerResponse;
import com.quiz.quiz.dto.answer.QuestionAnswerResponse;
import com.quiz.quiz.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerConverter {

    public CreateAnswerResponse toCreateAnswerResponse(Answer answer) {

        return new CreateAnswerResponse()
                .setText(answer.getText())
                .setImage(answer.getImage())
                .setIsCorrect(answer.getIsCorrect());
    }

    public Answer toAnswer(CreateAnswerRequest createAnswerRequest) {

        return new Answer()
                .setText(createAnswerRequest.getText())
                .setImage(createAnswerRequest.getImage())
                .setIsCorrect(createAnswerRequest.getIsCorrect());
    }
}
