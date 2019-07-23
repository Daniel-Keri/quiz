package com.quiz.quiz.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@Accessors(chain=true)
public class AccountRequest {

    private String email;

    private String password;
}
