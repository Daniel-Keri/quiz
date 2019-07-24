package com.quiz.quiz.services.accounts;

import com.quiz.quiz.converter.AccountConverter;
import com.quiz.quiz.dto.account.CreateAdminAccountRequest;
import com.quiz.quiz.dto.account.CreateAdminAccountResponse;
import com.quiz.quiz.dto.account.GetAdminAccountDataResponse;
import com.quiz.quiz.entity.Account;
import com.quiz.quiz.entity.AdminAccount;
import com.quiz.quiz.exceptions.AccountNotFoundException;
import com.quiz.quiz.repository.accounts.AccountRepository;
import com.quiz.quiz.repository.accounts.AdminAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAccountService {

    private final AdminAccountRepository adminAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;

    public CreateAdminAccountResponse createAdminAccount(CreateAdminAccountRequest createAdminAccountRequest){

        AdminAccount adminAccount = accountConverter.toAdminAccount(createAdminAccountRequest);
        adminAccountRepository.save(adminAccount);

        return (CreateAdminAccountResponse) new CreateAdminAccountResponse().setId(adminAccount.getId());
    }

    public GetAdminAccountDataResponse getUserAccountData() {

        Account account = accountRepository.getAccountData().orElseThrow(AccountNotFoundException::new);

        return accountConverter.toGetAdminAccountDataResponse(account);
    }
}
