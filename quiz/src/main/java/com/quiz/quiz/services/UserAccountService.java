package com.quiz.quiz.services;

import com.quiz.quiz.converter.AccountConverter;
import com.quiz.quiz.dto.account.CreateUserAccountResponse;
import com.quiz.quiz.dto.account.CreateUserAccountRequest;
import com.quiz.quiz.entity.UserAccount;
import com.quiz.quiz.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final AccountConverter accountConverter;

    public CreateUserAccountResponse createUserAccount(CreateUserAccountRequest createUserAccountRequest){

        UserAccount userAccount = accountConverter.toUserAccount(createUserAccountRequest);
        userAccountRepository.save(userAccount);

        return (CreateUserAccountResponse) new CreateUserAccountResponse().setId(userAccount.getId());
    }
}
