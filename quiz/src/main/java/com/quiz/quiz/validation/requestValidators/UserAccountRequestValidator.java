package com.quiz.quiz.validation.requestValidators;

import com.quiz.quiz.dto.account.CreateUserAccountRequest;
import com.quiz.quiz.repository.accounts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.quiz.quiz.validation.constants.ValidatorConstants.EMAIL_PATTERN;

@RequiredArgsConstructor
@Component
public class UserAccountRequestValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateUserAccountRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        CreateUserAccountRequest createUserAccountRequest = (CreateUserAccountRequest) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "REQUIRED", "the email must not be null or empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "REQUIRED", "the password must not be null or empty");

        String email = createUserAccountRequest.getEmail();

        if (email != null) {
            if (!isValidEmail(email)) {
                errors.rejectValue("email", "REQUIRED", "invalid email");
            }

            if (accountRepository.findByEmail(email).isPresent()) {
                errors.rejectValue("email", "ALREADY_EXISTS", "EMAIL ALREADY EXISTS");
            }
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
