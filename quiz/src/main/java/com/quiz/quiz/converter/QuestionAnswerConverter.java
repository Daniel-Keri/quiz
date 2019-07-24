package com.quiz.quiz.converter;


import com.quiz.quiz.dto.answer.QuestionAnswerResponse;
import com.quiz.quiz.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionAnswerConverter {

    public QuestionAnswerResponse toQuestionAnswerResponse(Answer answer){

        return new QuestionAnswerResponse()
                .setText(answer.getText())
                .setImage(answer.getImage())
                .setIsCorrect(answer.getIsCorrect());
    }
}
