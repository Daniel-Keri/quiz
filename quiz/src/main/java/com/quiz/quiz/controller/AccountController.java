package com.quiz.quiz.controller;

import com.quiz.quiz.repository.AccountRepository;
import com.quiz.quiz.validation.requestValidators.AccountRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountRequestValidator accountRequestValidator;
}
