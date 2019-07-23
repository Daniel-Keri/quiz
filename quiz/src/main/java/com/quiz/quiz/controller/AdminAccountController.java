package com.quiz.quiz.controller;

import com.quiz.quiz.dto.account.CreateAdminAccountRequest;
import com.quiz.quiz.dto.account.CreateAdminAccountResponse;
import com.quiz.quiz.services.AdminAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("adminAccounts")
@RequiredArgsConstructor
public class AdminAccountController {

    private final AdminAccountService adminAccountService;

    @PostMapping
    public CreateAdminAccountResponse createAdminAccount(@RequestBody CreateAdminAccountRequest createAdminAccountRequest){

        return adminAccountService.createAdminAccount(createAdminAccountRequest);
    }
}
