package com.quiz.quiz.dto.account;

import com.quiz.quiz.enums.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain=true)
public class GetAccountDataResponse {

    private UUID id;

    private String username;

    private String email;

    private Role role;
}
