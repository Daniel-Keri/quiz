package com.quiz.quiz.converter;

import com.quiz.quiz.dto.account.*;
import com.quiz.quiz.entity.Account;
import com.quiz.quiz.entity.AdminAccount;
import com.quiz.quiz.entity.UserAccount;
import com.quiz.quiz.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountConverter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAccount toUserAccount(CreateUserAccountRequest createUserAccountRequest){

        return (UserAccount) new UserAccount()
                .setUsername(createUserAccountRequest.getUsername())
                .setPassword(bCryptPasswordEncoder.encode(createUserAccountRequest.getPassword()))
                .setEmail(createUserAccountRequest.getEmail())
                .setRole(Role.ROLE_USER);
    }

    public AdminAccount toAdminAccount(CreateAdminAccountRequest createAdminAccountRequest) {

        return (AdminAccount) new AdminAccount()
                .setUsername(createAdminAccountRequest.getUsername())
                .setPassword(bCryptPasswordEncoder.encode(createAdminAccountRequest.getPassword()))
                .setEmail(createAdminAccountRequest.getEmail())
                .setRole(Role.ROLE_ADMIN);
    }

    public GetUserAccountDataResponse toGetUserAccountDataResponse(Account account) {

        return new GetUserAccountDataResponse()
                .setId(account.getId())
                .setRole(account.getRole())
                .setUsername(account.getUsername())
                .setEmail(account.getEmail());
    }

    public GetAdminAccountDataResponse toGetAdminAccountDataResponse(Account account) {

        return new GetAdminAccountDataResponse()
                .setId(account.getId())
                .setRole(account.getRole())
                .setUsername(account.getUsername())
                .setEmail(account.getEmail());
    }
    public UpdateAdminAccountResponse toUpdateAdminAccountResponse(AdminAccount adminAccount){

        return new UpdateAdminAccountResponse()
                .setUsername(adminAccount.getUsername());
    }
    public UpdateUserAccountResponse toUpdateUserAccountResponse(UserAccount userAccount){

        return new UpdateUserAccountResponse()
                .setUsername(userAccount.getUsername());
    }
}
