package com.quiz.quiz.converter;

import com.quiz.quiz.dto.account.CreateAdminAccountRequest;
import com.quiz.quiz.dto.account.CreateUserAccountRequest;
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
}
