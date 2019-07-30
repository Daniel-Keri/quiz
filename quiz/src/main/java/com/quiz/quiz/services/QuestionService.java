package com.quiz.quiz.services;

import com.quiz.quiz.converter.AnswerConverter;
import com.quiz.quiz.converter.QuestionConverter;
import com.quiz.quiz.dto.answer.QuestionAnswerResponse;
import com.quiz.quiz.dto.question.AllQuestionByThemeResponse;
import com.quiz.quiz.dto.question.CreateQuestionRequest;
import com.quiz.quiz.dto.question.CreateQuestionResponse;
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

    // POST
    public CreateQuestionResponse createQuestion(CreateQuestionRequest createQuestionRequest) {

        return questionConverter.toCreateQuestionResponse(
                questionRepository.save(questionConverter.toCreateQuestion(createQuestionRequest)));
    }

    // GET
    public Page<ThemeResponse> findAllTheme(Pageable pageable) {

        List<ThemeResponse> themeResponses= questionRepository.findAllTheme().stream()
                .map(questionConverter::toThemeResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(themeResponses,pageable,themeResponses.size()) ;
    }

    public Page<AllQuestionByThemeResponse> findAllByTheme(String theme, Pageable pageable) {

        List<AllQuestionByThemeResponse> questionList = questionRepository.getAllQuestionsByThemeResponses(theme);

        return new PageImpl<>(questionList, pageable, questionList.size());
    }

    public Page<AllQuestionByThemeResponse> findAllByThemeRandomized(String theme, Pageable pageable) {

        List<AllQuestionByThemeResponse> questionList = questionRepository.getAllQuestionsByThemeResponses(theme);
        Collections.shuffle(questionList);

        return new PageImpl<>(questionList, pageable, questionList.size());
    }

    public Page<QuestionAnswerResponse> findAllQuestionAnswers(UUID id, Pageable pageable) throws QuestionNotFoundException {

        List<Answer> answers = questionRepository.findAllQuestionAnswersById(id);
        if (answers.isEmpty() || answers.size() == 0) { throw new QuestionNotFoundException();}

        List<QuestionAnswerResponse> questionAnswerResponses = answers.stream()
                .map(answerConverter::toQuestionAnswerResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(questionAnswerResponses, pageable, answers.size());
    }

    // DELETE
    public void deleteQuestion(UUID id) throws QuestionNotFoundException {

        questionRepository.delete(questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new));
    }

    public void deleteQuestions(List<UUID> ids) throws QuestionNotFoundException {

        List<Question> questions = questionRepository.findAllById(ids);

        if (questions.isEmpty()) {
            throw new QuestionNotFoundException();
        } else {
            questions.forEach(questionRepository::delete);
        }
    }
}
