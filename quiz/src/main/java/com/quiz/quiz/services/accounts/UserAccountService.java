package com.quiz.quiz.services.accounts;

import com.quiz.quiz.converter.AccountConverter;
import com.quiz.quiz.dto.account.*;
import com.quiz.quiz.entity.UserAccount;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.repository.accounts.UserAccountRepository;
import com.quiz.quiz.security.CustomUserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final AccountConverter accountConverter;

    // POST
    public CreateUserAccountResponse createUserAccount(CreateUserAccountRequest createUserAccountRequest) {

        UserAccount userAccount = accountConverter.toUserAccount(createUserAccountRequest);
        userAccountRepository.save(userAccount);

        return new CreateUserAccountResponse().setId(userAccount.getId());
    }

    // GET
    public GetUserAccountDataResponse getUserAccountData() {

        UserAccount userAccount = userAccountRepository
                .getUserAccountData(getLoggedInAccountId())
                .orElseThrow(EntityNotFoundException::new);

        return accountConverter.toGetUserAccountDataResponse(userAccount);
    }

    // UPDATE
    @Transactional
    public UpdateUserAccountResponse updateUserAccount(UpdateUserAccountRequest updateUserAccountRequest) throws EntityNotFoundException {
        UserAccount userAccount = userAccountRepository.getUserAccountData(getLoggedInAccountId()).orElseThrow(EntityNotFoundException::new);

        if (updateUserAccountRequest.getPassword() != null) {
            userAccount.setPassword(new BCryptPasswordEncoder().encode(updateUserAccountRequest.getPassword()));
        }
        if (updateUserAccountRequest.getUsername() != null) {
            userAccount.setUsername(updateUserAccountRequest.getUsername());
        }
        userAccountRepository.save(userAccount);
        return accountConverter.toUpdateUserAccountResponse(userAccount);
    }

    private UUID getLoggedInAccountId() {

        return ((CustomUserImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}
