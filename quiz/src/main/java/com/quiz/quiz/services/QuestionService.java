package com.quiz.quiz.services;

import com.quiz.quiz.converter.QuestionConverter;
import com.quiz.quiz.dto.QuestionRequest;
import com.quiz.quiz.dto.QuestionResponse;

import com.quiz.quiz.exceptions.QuestionNotFoundExcption;
import com.quiz.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionConverter questionConverter;
    private final QuestionRepository questionRepository;

    public QuestionResponse createQuestion(QuestionRequest questionRequest)
    {
        return questionConverter.toResponse(questionRepository.save(questionConverter.toQuestion(questionRequest)));
    }
    /*public QuestionResponse getRandomQuestionsForTheme(String theme,int count){


    }*/
    public void deleteQuestion(UUID id) throws QuestionNotFoundExcption {
        questionRepository.delete(questionRepository.findById(id).orElseThrow(QuestionNotFoundExcption::new));
    }
    public void deleteQuestions(List<UUID> ids) throws QuestionNotFoundExcption{
        questionRepository.findAllById(ids).forEach(questionRepository::delete);
    }

}
