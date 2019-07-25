package com.quiz.quiz.converter;

import com.quiz.quiz.dto.question.CreateQuestionRequest;
import com.quiz.quiz.dto.question.CreateQuestionResponse;
import com.quiz.quiz.dto.question.QuestionScoreResponse;
import com.quiz.quiz.dto.question.ThemeResponse;
import com.quiz.quiz.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionConverter {

    private final AnswerConverter answerConverter;

    public QuestionScoreResponse toQuestionScoreResponse(Question question, UUID chosenAnswerId)
    {
        return new QuestionScoreResponse()
                .setChosenAnswerId(chosenAnswerId)
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
                .setScore(question.getPoints())
                .setImage(question.getImage())
                .setText(question.getText());
    }
    public Question toCreateQuestion(CreateQuestionRequest createQuestionRequest) {

        return new Question()
                .setAnswers(createQuestionRequest.getAnswers().stream()
                        .map(answerConverter::toAnswer)
                        .collect(Collectors.toList()))
                .setTheme(createQuestionRequest.getTheme())
                .setPoints(createQuestionRequest.getScore())
                .setImage(createQuestionRequest.getImage())
                .setText(createQuestionRequest.getText());
    }

    public ThemeResponse toThemeResponse(String theme) {

        return new ThemeResponse()
                .setTheme(theme);
    }
}
