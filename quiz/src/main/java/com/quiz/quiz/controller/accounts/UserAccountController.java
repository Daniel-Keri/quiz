package com.quiz.quiz.controller.accounts;


import com.quiz.quiz.dto.account.*;
import com.quiz.quiz.services.accounts.UserAccountService;
import com.quiz.quiz.validation.requestValidators.account.AccountRequestValidator;
import com.quiz.quiz.validation.requestValidators.account.UpdateUserAccountRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static com.quiz.quiz.config.constants.URIConstants.USER_ACCOUNT;

@RestController
@RequestMapping(USER_ACCOUNT)
@RequiredArgsConstructor
// ONLY 'GET' AND 'PATCH' REQUEST TYPE PATH IS LINKED TO AUTHORIZATION (in securityConfig) HERE!
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final AccountRequestValidator accountRequestValidator;
    private final UpdateUserAccountRequestValidator updateAccountRequestValidator;

    @InitBinder("createUserAccountRequest")
    protected void initCreateUserAccountRequestValidatorBinder(WebDataBinder binder) {
        binder.addValidators(accountRequestValidator);
    }
    @InitBinder("updateUserRequestValidator")
    protected void initupdateUserAccountRequestValidatorBinder(WebDataBinder binder) {
        binder.addValidators(updateAccountRequestValidator);
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

    // PATCH
    @PatchMapping("/update")
    public UpdateUserAccountResponse updateUserAccount(@Validated @RequestBody UpdateUserAccountRequest updateUserAccountRequest){

        return userAccountService.updateUserAccount(updateUserAccountRequest);
    }
}
