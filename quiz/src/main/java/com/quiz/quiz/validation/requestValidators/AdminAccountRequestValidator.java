package com.quiz.quiz.validation.requestValidators;

import com.quiz.quiz.dto.account.CreateAdminAccountRequest;
import com.quiz.quiz.repository.accounts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.quiz.quiz.validation.constants.ValidatorConstants.EMAIL_PATTERN;

@RequiredArgsConstructor
@Component
public class AdminAccountRequestValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateAdminAccountRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        CreateAdminAccountRequest createAdminAccountRequest = (CreateAdminAccountRequest) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "REQUIRED", "the email must not be null or empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "REQUIRED", "the password must not be null or empty");

        String email = createAdminAccountRequest.getEmail();

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
