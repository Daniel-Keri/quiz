package com.quiz.quiz.converter;

import com.quiz.quiz.dto.QuestionRequest;
import com.quiz.quiz.dto.QuestionResponse;
import com.quiz.quiz.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionConverter {

    public Question toQuestion(QuestionRequest questionRequest) {
        return (Question) new Question()
                .setScore(questionRequest.getScore())
                .setText(questionRequest.getText())
                .setAnswers(questionRequest.getAnswers())
                .setTheme(questionRequest.getTheme())
                .setId(questionRequest.getId());
    }

    public QuestionResponse toResponse(Question question)
    {
        return new QuestionResponse()
                .setScore(question.getScore())
                .setText(question.getText())
                .setAnswers(question.getAnswers())
                .setTheme(question.getTheme())
                .setId(question.getId());
    }
}
