package com.quiz.quiz.controller.accounts;

import com.quiz.quiz.config.constants.URINameConstants;
import com.quiz.quiz.converter.AccountConverter;
import com.quiz.quiz.dto.account.CreateAdminAccountRequest;
import com.quiz.quiz.dto.account.CreateAdminAccountResponse;
import com.quiz.quiz.dto.account.GetAdminAccountDataResponse;
import com.quiz.quiz.dto.account.GetUserAccountDataResponse;
import com.quiz.quiz.entity.Account;
import com.quiz.quiz.exceptions.AccountNotFoundException;
import com.quiz.quiz.repository.accounts.AccountRepository;
import com.quiz.quiz.services.accounts.AdminAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URINameConstants.ADMIN_ACCOUNT)
@RequiredArgsConstructor
public class AdminAccountController {

    private final AdminAccountService adminAccountService;

    // POST
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')") // MIGHT BE REDUNDANT DEPENDING ON SECURITY_CONFIG
    public CreateAdminAccountResponse createAdminAccount(@Validated @RequestBody CreateAdminAccountRequest createAdminAccountRequest){

        return adminAccountService.createAdminAccount(createAdminAccountRequest);
    }

    // GET
    @GetMapping
    public GetAdminAccountDataResponse getUserAccountData() {

        return adminAccountService.getUserAccountData();
    }
}
