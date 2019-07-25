package com.quiz.quiz.services;

import com.quiz.quiz.converter.AnswerConverter;
import com.quiz.quiz.converter.QuestionConverter;
import com.quiz.quiz.dto.answer.QuestionAnswerRequest;
import com.quiz.quiz.dto.answer.QuestionAnswerResponse;
import com.quiz.quiz.dto.question.CreateQuestionRequest;
import com.quiz.quiz.dto.question.CreateQuestionResponse;
import com.quiz.quiz.dto.question.QuestionScoreResponse;
import com.quiz.quiz.dto.question.ThemeResponse;
import com.quiz.quiz.entity.Answer;
import com.quiz.quiz.entity.Question;
import com.quiz.quiz.exceptions.QuestionNotFoundException;
import com.quiz.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionConverter questionConverter;
    private final AnswerConverter answerConverter;
    private final QuestionRepository questionRepository;

    //SAVE
    public CreateQuestionResponse createQuestion(CreateQuestionRequest createQuestionRequest){

        return questionConverter.toCreateQuestionResponse(
                questionRepository.save(questionConverter.toCreateQuestion(createQuestionRequest)));
    }

    //GET
    public List<ThemeResponse> findAllTheme() {

        return questionRepository.findAllTheme().stream()
                .map(questionConverter::toThemeResponse)
                .collect(Collectors.toList());
    }

    public Page<QuestionScoreResponse> findAllByTheme(String theme, Pageable pageable) {

        Page<Question> questionPage = questionRepository.findAllByTheme(theme, pageable);

        List<Question> questionList = questionPage.getContent();

        Page<QuestionScoreResponse> questionScoreResponsePage = new PageImpl<>(
                questionList.stream()
                        .map(questionConverter::toQuestionScoreResponse)
                        .collect(Collectors.toList()),
                pageable,
                questionList.size());

        return questionScoreResponsePage;
    }

    public Page<QuestionScoreResponse> findAllByThemeRandomized(String theme, Pageable pageable) {

        Page<Question> questionPage = questionRepository.findAllByTheme(theme, pageable);

        List<Question> questionList = new ArrayList<>(questionPage.getContent());
        Collections.shuffle(questionList);

        Page<QuestionScoreResponse> questionScoreResponsePage = new PageImpl<>(
                questionList.stream()
                        .map(questionConverter::toQuestionScoreResponse)
                        .collect(Collectors.toList()),
                pageable,
                questionList.size());

        return questionScoreResponsePage;
    }

    public List<QuestionAnswerResponse> findAllQuestionAnswers(QuestionAnswerRequest questionAnswerRequest) {

        List<Answer> answers = questionRepository
                .findById(questionAnswerRequest.getId())
                .orElseThrow(QuestionNotFoundException::new)
                .getAnswers();

        return answers.stream()
                .map(answerConverter::toQuestionAnswerResponse)
                .collect(Collectors.toList());
    }

    //DELETE
    public void deleteQuestion(UUID id) throws QuestionNotFoundException {
        questionRepository.delete(questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new));
    }

    public void deleteQuestions(List<UUID> ids) throws QuestionNotFoundException {
        questionRepository.findAllById(ids).forEach(questionRepository::delete);
    }
}
