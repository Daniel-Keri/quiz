package com.quiz.quiz.converter;

import com.quiz.quiz.dto.answer.CreateAnswerResponse;
import com.quiz.quiz.dto.question.*;
import com.quiz.quiz.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionConverter {

    private final AnswerConverter answerConverter;

    public Question toQuestion(QuestionRequest questionRequest) {
        return new Question()
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

    public QuestionScoreResponse toQuestionScoreResponse(Question question)
    {
        return new QuestionScoreResponse()
                .setUserScore(question.getScore())
                .setText(question.getText())
                .setImage(question.getImage())
                .setId(question.getId());
    }

    public CreateQuestionResponse toCreateQuestionResponse(Question question) {


        return new CreateQuestionResponse()
                .setAnswers(question.getAnswers().stream()
                        .map(answerConverter::toCreateAnswerResponse)
                        .collect(Collectors.toList()))
                .setId(question.getId())
                .setTheme(question.getTheme())
                .setScore(question.getScore())
                .setImage(question.getImage())
                .setText(question.getText());
    }
    public Question toCreateQuestion(CreateQuestionRequest createQuestionRequest) {

        return new Question()
                .setAnswers(createQuestionRequest.getAnswers().stream()
                        .map(answerConverter::toAnswer)
                        .collect(Collectors.toList()))
                .setTheme(createQuestionRequest.getTheme())
                .setScore(createQuestionRequest.getScore())
                .setImage(createQuestionRequest.getImage())
                .setText(createQuestionRequest.getText());
    }
}
