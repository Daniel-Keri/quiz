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

import static com.quiz.quiz.validation.constants.ValidatorConstants.*;

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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answers", "REQUIRED", "The answers must not be null or empty");

        List<CreateAnswerRequest> createAnswerRequests = createQuestionRequest.getAnswers();

        if (createAnswerRequests == null || createAnswerRequests.isEmpty()) {
            errors.rejectValue("answers", "REQUIRED", "invalid answers");
        }
        else {
            if (createAnswerRequests.size() < MIN_ANSWER || createAnswerRequests.size() > MAX_ANSWER) {
                errors.rejectValue("answers", "INVALID", String.format(
                        "Incorrect number of answers. The minimum is: %s and the maximum is: %s", MIN_ANSWER, MAX_ANSWER));
            }

            int count = 0;
            for (CreateAnswerRequest answer : createAnswerRequests) {
                count += answer.getIsCorrect() ? 1 : 0;
            }
            if (count < MIN_CORRECT_ANSWER || count > MAX_CORRECT_ANSWER) {
                errors.rejectValue("answers", "INVALID", String.format(
                        "Incorrect number of correct answers. The minimum is: %s and the maximum is: %s", MIN_CORRECT_ANSWER, MAX_CORRECT_ANSWER));
            }
        }
    }
}
