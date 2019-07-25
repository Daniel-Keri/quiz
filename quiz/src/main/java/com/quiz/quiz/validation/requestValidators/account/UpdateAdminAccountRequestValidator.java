package com.quiz.quiz.validation.requestValidators.account;

import com.quiz.quiz.dto.account.UpdateAdminAccountRequest;
import com.quiz.quiz.repository.accounts.AdminAccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UpdateAdminAccountRequestValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateAdminAccountRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UpdateAdminAccountRequest updateAdminAccountRequest = (UpdateAdminAccountRequest) object;

        if (updateAdminAccountRequest.getUsername()!=null ) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "REQUIRED", "the username must not be null or empty");
        }
        if (updateAdminAccountRequest.getPassword()!=null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "REQUIRED", "the password must not be null or empty");
        }
        if (updateAdminAccountRequest.getPassword()!=null && updateAdminAccountRequest.getUsername()!=null){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "REQUIRED", "the password must not be null or empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "REQUIRED", "the username must not be null or empty");
        }

    }
}
