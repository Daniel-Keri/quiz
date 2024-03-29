package com.quiz.quiz.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class UpdateAdminAccountRequest {

    private String username;

    private String password;
}
