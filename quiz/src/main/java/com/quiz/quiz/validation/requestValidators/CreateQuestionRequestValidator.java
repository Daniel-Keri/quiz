package com.quiz.quiz.validation.requestValidators;

import com.quiz.quiz.dto.answer.CreateAnswerRequest;
import com.quiz.quiz.dto.question.CreateQuestionRequest;
import com.quiz.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CreateQuestionRequestValidator implements Validator {

    private final QuestionRepository questionRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateQuestionRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        CreateQuestionRequest createQuestionRequest = (CreateQuestionRequest) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answers", "REQUIRED", "the answers must not be null or empty");

        List<CreateAnswerRequest> createAnswerRequests = createQuestionRequest.getAnswers();

        if (createAnswerRequests != null) {
            if (createAnswerRequests.isEmpty()) {
                errors.rejectValue("answers", "REQUIRED", "invalid answers");
            }

            int correctAnswerCount = 0;
            for (CreateAnswerRequest s : createAnswerRequests) {
                if(s.getIsCorrect()) {
                    correctAnswerCount++;
                }
            }
            if (correctAnswerCount > 1 || correctAnswerCount == 0) {
                errors.rejectValue("answers", "INCORRECT_NUMBER_OF_CORRECT_ANSWERS", "invalid answers");
            }
        }
    }
}
