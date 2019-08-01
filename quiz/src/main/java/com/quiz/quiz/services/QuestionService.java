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
import com.quiz.quiz.repository.AnswerRepository;
import com.quiz.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
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

    // POST
    public CreateQuestionResponse createQuestion(CreateQuestionRequest createQuestionRequest) {

        return questionConverter.toCreateQuestionResponse(
                questionRepository.save(questionConverter.toCreateQuestion(createQuestionRequest)));
    }

    // GET
    public Page<ThemeResponse> findAllTheme(Pageable pageable) {

        List<ThemeResponse> themeResponses = questionRepository.findAllTheme().stream()
                .map(questionConverter::toThemeResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(themeResponses, pageable, themeResponses.size());
    }

    public Page<AllQuestionByThemeResponse> findAllByTheme(String theme, Pageable pageable) {

        return questionRepository.getAllQuestionsByThemeResponses(theme, pageable);
    }

    public Page<AllQuestionByThemeResponse> findAllByThemeRandomized(String theme, Pageable pageable) {

        List<AllQuestionByThemeResponse> questionList = questionRepository.getAllQuestionsByThemeResponses(theme);
        Collections.shuffle(questionList);

        return new PageImpl<>(questionList, pageable, questionList.size());
    }

    public Page<QuestionAnswerResponse> findAllQuestionAnswers(UUID id, Pageable pageable) throws EntityNotFoundException {

        List<Answer> answers = questionRepository.findAllQuestionAnswersById(id);
        if (answers.isEmpty()) { throw new EntityNotFoundException();} else {
            answers.size();
        }

        List<QuestionAnswerResponse> questionAnswerResponses = answers.stream()
                .map(answerConverter::toQuestionAnswerResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(questionAnswerResponses, pageable, answers.size());
    }

    // DELETE
    public void deleteQuestion(UUID id) throws EntityNotFoundException {

        questionRepository.delete(questionRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public void deleteQuestions(List<UUID> ids) throws EntityNotFoundException {

        List<Question> questions = questionRepository.findAllById(ids);

        if (questions.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            questions.forEach(questionRepository::delete);
        }
    }

    ////    @Transactional
//    public void deleteQuestionsByThemes(List<String> themes) {
//
//       questionRepository.deleteQuestionsByThemes(themes);
//    }
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
