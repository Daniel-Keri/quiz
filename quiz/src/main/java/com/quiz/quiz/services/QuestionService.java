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
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.principal.CustomPrincipal;
import com.quiz.quiz.repository.AnswerRepository;
import com.quiz.quiz.repository.QuestionRepository;
import com.quiz.quiz.security.CustomUserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AnswerRepository answerRepository;
    private final CustomPrincipal customPrincipal;

    // POST
    public CreateQuestionResponse createQuestion(CreateQuestionRequest createQuestionRequest) {

        Question question = questionConverter.toCreateQuestion(createQuestionRequest);
        List<Answer> answers = question.getAnswers();

        question = questionRepository.save(question);
        //answerRepository.saveAll(answers);
        return questionConverter.toCreateQuestionResponse(question);
    }

    // GET
    public Page<ThemeResponse> findAllTheme(Pageable pageable) {

        List<ThemeResponse> themeResponses = questionRepository.findAllTheme().stream()
                .map(questionConverter::toThemeResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(themeResponses, pageable, themeResponses.size());
    }

    public Page<AllQuestionByThemeResponse> findAllByTheme(String theme, Pageable pageable) {

        return questionRepository.getAllQuestionsByThemeResponses(theme, customPrincipal.getLoggedInAccountId(), pageable);
    }

    public Page<AllQuestionByThemeResponse> findAllByThemeRandomized(String theme, Pageable pageable) {

        List<AllQuestionByThemeResponse> questionList = questionRepository.getAllQuestionsByThemeResponses(theme, customPrincipal.getLoggedInAccountId());
        Collections.shuffle(questionList);

        return new PageImpl<>(questionList, pageable, questionList.size());
    }

    public Page<QuestionAnswerResponse> findAllQuestionAnswers(UUID id, Pageable pageable) throws EntityNotFoundException {

        List<Answer> answers = questionRepository.findAllQuestionAnswersById(id);
        if (answers.isEmpty()) { throw new EntityNotFoundException();}

        List<QuestionAnswerResponse> questionAnswerResponses = answers.stream()
                .map(answerConverter::toQuestionAnswerResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(questionAnswerResponses, pageable, answers.size());
    }

    // DELETE
    public void deleteQuestion(UUID questionId) throws EntityNotFoundException {

        Question question = questionRepository.findByIdEager(questionId).orElseThrow(EntityNotFoundException::new);
        List<Answer> answers = question.getAnswers();

        answerRepository.deleteInBatch(answers);
        questionRepository.delete(question);
    }

    public void deleteQuestions(List<UUID> ids) throws EntityNotFoundException {

        List<Question> questions = questionRepository.findAllByIdIn(ids);
        List<Answer> answers = questions.stream()
                .flatMap(question -> question.getAnswers().stream())
                .collect(Collectors.toList());

        if (questions.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            answerRepository.deleteInBatch(answers);
            questionRepository.deleteInBatch(questions);
        }
    }

    public void deleteQuestionsByThemes(List<String> themes) {

        List<Question> questions = questionRepository.findAllByThemeIn(themes);
        List<Answer> answers = questions.stream()
                .flatMap(question -> question.getAnswers().stream())
                .collect(Collectors.toList());

        if (questions.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            answerRepository.deleteInBatch(answers);
            questionRepository.deleteInBatch(questions);
        }
    }
}
