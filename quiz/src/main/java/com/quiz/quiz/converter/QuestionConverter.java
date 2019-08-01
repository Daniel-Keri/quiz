package com.quiz.quiz.converter;

import com.quiz.quiz.dto.question.*;
import com.quiz.quiz.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionConverter {

    private final AnswerConverter answerConverter;

    public CreateQuestionResponse toCreateQuestionResponse(Question question) {

        return new CreateQuestionResponse()
                .setAnswers(question.getAnswers().stream()
                        .map(answerConverter::toCreateAnswerResponse)
                        .collect(Collectors.toList()))
                .setId(question.getId())
                .setTheme(question.getTheme())
                .setPoints(question.getPoints())
                .setImage(question.getImage())
                .setText(question.getText());
    }

    public Question toCreateQuestion(CreateQuestionRequest createQuestionRequest) {

        Question question = new Question()
                .setTheme(createQuestionRequest.getTheme())
                .setPoints(createQuestionRequest.getPoints())
                .setImage(createQuestionRequest.getImage())
                .setText(createQuestionRequest.getText());

        question.setAnswers(createQuestionRequest.getAnswers().stream()
                .map((createAnswerRequest) -> answerConverter.toAnswer(createAnswerRequest, question))
                .collect(Collectors.toList()));

        return question;
    }

    public ThemeResponse toThemeResponse(String theme) {

        return new ThemeResponse()
                .setTheme(theme);
    }
}
