package com.quiz.quiz.validation.requestValidators.account;

import com.quiz.quiz.dto.account.UpdateUserAccountRequest;
import com.quiz.quiz.repository.accounts.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UpdateUserAccountRequestValidator implements Validator {
    private final UserAccountRepository userAccountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateUserAccountRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        UpdateUserAccountRequest updateUserAccountRequest = (UpdateUserAccountRequest) object;

        if (updateUserAccountRequest.getUsername()!=null ) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "REQUIRED", "The username must not be null or empty");
        }
        if (updateUserAccountRequest.getPassword()!=null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "REQUIRED", "The password must not be null or empty");
        }
        if (updateUserAccountRequest.getPassword()!=null && updateUserAccountRequest.getUsername()!=null){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "REQUIRED", "The password must not be null or empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "REQUIRED", "The username must not be null or empty");
        }
    }
}

