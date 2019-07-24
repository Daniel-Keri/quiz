package com.quiz.quiz.services.accounts;

import com.quiz.quiz.converter.AccountConverter;
import com.quiz.quiz.dto.account.CreateUserAccountResponse;
import com.quiz.quiz.dto.account.CreateUserAccountRequest;
import com.quiz.quiz.dto.account.GetUserAccountDataResponse;
import com.quiz.quiz.entity.Account;
import com.quiz.quiz.entity.UserAccount;
import com.quiz.quiz.exceptions.AccountNotFoundException;
import com.quiz.quiz.repository.accounts.AccountRepository;
import com.quiz.quiz.repository.accounts.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;

    // POST
    public CreateUserAccountResponse createUserAccount(CreateUserAccountRequest createUserAccountRequest){

        UserAccount userAccount = accountConverter.toUserAccount(createUserAccountRequest);
        userAccountRepository.save(userAccount);

        return (CreateUserAccountResponse) new CreateUserAccountResponse().setId(userAccount.getId());
    }

    // GET
    public GetUserAccountDataResponse getUserAccountData() {

        Account account = accountRepository.getAccountData().orElseThrow(AccountNotFoundException::new);

        return accountConverter.toGetUserAccountDataResponse(account);
    }
}
