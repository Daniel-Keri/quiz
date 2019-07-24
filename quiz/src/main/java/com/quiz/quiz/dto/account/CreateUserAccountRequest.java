package com.quiz.quiz.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

@Data
@Accessors(chain=true)
public class CreateUserAccountRequest {

    private String email;

    private String password;

    private String username;
}
