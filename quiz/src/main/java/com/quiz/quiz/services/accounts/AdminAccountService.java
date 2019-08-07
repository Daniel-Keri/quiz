package com.quiz.quiz.services.accounts;

import com.quiz.quiz.converter.AccountConverter;
import com.quiz.quiz.dto.account.*;
import com.quiz.quiz.entity.AdminAccount;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import com.quiz.quiz.repository.accounts.AdminAccountRepository;
import com.quiz.quiz.security.CustomUserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminAccountService {

    private final AdminAccountRepository adminAccountRepository;
    private final AccountConverter accountConverter;

    // POST
    public CreateAdminAccountResponse createAdminAccount(CreateAdminAccountRequest createAdminAccountRequest) {

        AdminAccount adminAccount = accountConverter.toAdminAccount(createAdminAccountRequest);
        adminAccountRepository.save(adminAccount);

        return new CreateAdminAccountResponse().setId(adminAccount.getId());
    }

    // GET
    public GetAdminAccountDataResponse getAdminAccountData() {

        AdminAccount adminAccount = adminAccountRepository
                .getAdminAccountData(getLoggedInAccountId())
                .orElseThrow(EntityNotFoundException::new);

        return accountConverter.toGetAdminAccountDataResponse(adminAccount);
    }

    // UPDATE
    @Transactional
    public UpdateAdminAccountResponse updateAdminAccount(UpdateAdminAccountRequest updateAdminAccountRequest) throws EntityNotFoundException {
        AdminAccount adminAccount = adminAccountRepository.getAdminAccountData(getLoggedInAccountId()).orElseThrow(EntityNotFoundException::new);

        if (updateAdminAccountRequest.getPassword() != null) {
            adminAccount.setPassword(new BCryptPasswordEncoder().encode(updateAdminAccountRequest.getPassword()));
        }
        if (updateAdminAccountRequest.getUsername() != null) {
            adminAccount.setUsername(updateAdminAccountRequest.getUsername());
        }
        adminAccountRepository.save(adminAccount);
        return accountConverter.toUpdateAdminAccountResponse(adminAccount);
    }

    private UUID getLoggedInAccountId() {

        return ((CustomUserImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}
