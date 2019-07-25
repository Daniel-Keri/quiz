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
import com.quiz.quiz.entity.AnsweredQuestion;
import com.quiz.quiz.entity.Question;
import com.quiz.quiz.entity.UserAccount;
import com.quiz.quiz.exceptions.QuestionNotFoundException;
import com.quiz.quiz.repository.AnsweredQuestionsRepository;
import com.quiz.quiz.repository.QuestionRepository;
import com.quiz.quiz.repository.accounts.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionConverter questionConverter;
    private final AnswerConverter answerConverter;
    private final QuestionRepository questionRepository;
    private final UserAccountRepository userAccountRepository;
    private final AnsweredQuestionsRepository answeredQuestionsRepository;

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

        List<AnsweredQuestion> answeredQuestions = answeredQuestionsRepository.findAllAnsweredQuestions();

        Page<Question> questionPage = questionRepository.findAllByTheme(theme, pageable);
        List<Question> questionList = questionPage.getContent();

        //Map<UUID, Double> answeredQuestions = userAccountRepository.findAnsweredQuestions();

        Page<QuestionScoreResponse> questionScoreResponsePage = new PageImpl<>((questionList.stream().map((question) -> {
            int index = 0;
            int i = 0;
            while (i < answeredQuestions.size()) {
                if (answeredQuestions.get(i).getQuestionId().equals(question.getId())) {
                    index = i;
                    break;
                } else {
                    index = -1;
                }
                i++;
            }
            return questionConverter.toQuestionScoreResponse(question, index == -1 ? null : answeredQuestions.get(index).getChosenAnswerId());
        })).collect(Collectors.toList()), pageable, questionList.size());

        return questionScoreResponsePage;
    }

    public Page<QuestionScoreResponse> findAllByThemeRandomized(String theme, Pageable pageable) {

        List<AnsweredQuestion> answeredQuestions = answeredQuestionsRepository.findAllAnsweredQuestions();

        Page<Question> questionPage = questionRepository.findAllByTheme(theme, pageable);
        List<Question> questionList = new ArrayList<>(questionPage.getContent());

        Collections.shuffle(questionList);

        Page<QuestionScoreResponse> questionScoreResponsePage = new PageImpl<>((questionList.stream().map((question) -> {
            int index = 0;
            int i = 0;
            while (i < answeredQuestions.size()) {
                if (answeredQuestions.get(i).getQuestionId().equals(question.getId())) {
                    index = i;
                    break;
                } else {
                    index = -1;
                }
                i++;
            }
            return questionConverter.toQuestionScoreResponse(question, index == -1 ? null : answeredQuestions.get(index).getChosenAnswerId());
        })).collect(Collectors.toList()), pageable, questionList.size());

        return questionScoreResponsePage;
    }

    public List<QuestionAnswerResponse> findAllQuestionAnswers(UUID id) throws QuestionNotFoundException {

        List<Answer> answers = questionRepository
                .findById(id)
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
