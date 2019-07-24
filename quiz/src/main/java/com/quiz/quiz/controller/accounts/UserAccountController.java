package com.quiz.quiz.controller.accounts;


import com.quiz.quiz.dto.account.CreateUserAccountResponse;
import com.quiz.quiz.dto.account.CreateUserAccountRequest;
import com.quiz.quiz.dto.account.GetUserAccountDataResponse;
import com.quiz.quiz.services.accounts.UserAccountService;
import com.quiz.quiz.validation.requestValidators.account.UserAccountRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static com.quiz.quiz.config.constants.URIConstants.USER_ACCOUNT;

@RestController
@RequestMapping(USER_ACCOUNT)
@RequiredArgsConstructor
// ONLY 'GET' REQUEST TYPE PATH IS LINKED TO AUTHORIZATION (in securityConfig) HERE!
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final UserAccountRequestValidator userAccountRequestValidator;

    @InitBinder("createUserAccountRequest")
    protected void initCreateUserAccountRequestValidatorBinder(WebDataBinder binder) {
        binder.addValidators(userAccountRequestValidator);
    }

    // POST
    @PostMapping
    public CreateUserAccountResponse createUserAccount(@Validated @RequestBody CreateUserAccountRequest createUserAccountRequest){

        return userAccountService.createUserAccount(createUserAccountRequest);
    }

    // GET
    @GetMapping
    public GetUserAccountDataResponse getUserAccountData() {

        return userAccountService.getUserAccountData();
    }
}
