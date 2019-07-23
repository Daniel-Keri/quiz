package com.quiz.quiz.controller;


import com.quiz.quiz.dto.account.CreateUserAccountResponse;
import com.quiz.quiz.dto.account.CreateUserAccountRequest;
import com.quiz.quiz.services.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userAccounts")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping
    public CreateUserAccountResponse createUserAccount(@RequestBody CreateUserAccountRequest createUserAccountRequest){

        return userAccountService.createUserAccount(createUserAccountRequest);
    }
}
