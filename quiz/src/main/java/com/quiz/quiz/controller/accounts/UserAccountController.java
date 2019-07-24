package com.quiz.quiz.controller.accounts;


import com.quiz.quiz.config.constants.URINameConstants;
import com.quiz.quiz.dto.account.CreateUserAccountResponse;
import com.quiz.quiz.dto.account.CreateUserAccountRequest;
import com.quiz.quiz.services.accounts.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URINameConstants.USER_ACCOUNT)
@RequiredArgsConstructor
// ONLY 'GET' REQUEST TYPE PATH IS LINKED TO AUTHORIZATION (in securityConfig) HERE!
public class UserAccountController {

    private final UserAccountService userAccountService;

    // POST
    @PostMapping
    public CreateUserAccountResponse createUserAccount(@Validated @RequestBody CreateUserAccountRequest createUserAccountRequest){

        return userAccountService.createUserAccount(createUserAccountRequest);
    }
}
