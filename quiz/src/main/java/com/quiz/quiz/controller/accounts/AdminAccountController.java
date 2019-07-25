package com.quiz.quiz.controller.accounts;

import com.quiz.quiz.dto.account.CreateAdminAccountRequest;
import com.quiz.quiz.dto.account.CreateAdminAccountResponse;
import com.quiz.quiz.dto.account.GetAdminAccountDataResponse;
import com.quiz.quiz.services.accounts.AdminAccountService;
import com.quiz.quiz.validation.requestValidators.account.AdminAccountRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static com.quiz.quiz.config.constants.URIConstants.ADMIN_ACCOUNT;

@RestController
@RequestMapping(ADMIN_ACCOUNT)
@RequiredArgsConstructor
public class AdminAccountController {

    private final AdminAccountService adminAccountService;
    private final AdminAccountRequestValidator adminAccountRequestValidator;

    @InitBinder("createAdminAccountRequest")
    protected void initCreateAdminAccountRequestValidatorBinder(WebDataBinder binder) {
        binder.addValidators(adminAccountRequestValidator);
    }

    // POST
    @PostMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')") // MIGHT BE REDUNDANT DEPENDING ON SECURITY_CONFIG
    public CreateAdminAccountResponse createAdminAccount(@Validated @RequestBody CreateAdminAccountRequest createAdminAccountRequest){

        return adminAccountService.createAdminAccount(createAdminAccountRequest);
    }

    // GET
    @GetMapping
    public GetAdminAccountDataResponse getUserAccountData() {

        return adminAccountService.getUserAccountData();
    }
}
