package com.quiz.quiz.controller.accounts;

import com.quiz.quiz.config.constants.URINameConstants;
import com.quiz.quiz.dto.account.CreateAdminAccountRequest;
import com.quiz.quiz.dto.account.CreateAdminAccountResponse;
import com.quiz.quiz.services.accounts.AdminAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URINameConstants.ADMIN_ACCOUNT)
@RequiredArgsConstructor
public class AdminAccountController {

    private final AdminAccountService adminAccountService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')") // MIGHT BE REDUNDANT DEPENDING ON SECURITY_CONFIG
    public CreateAdminAccountResponse createAdminAccount(@Validated @RequestBody CreateAdminAccountRequest createAdminAccountRequest){

        return adminAccountService.createAdminAccount(createAdminAccountRequest);
    }
}
