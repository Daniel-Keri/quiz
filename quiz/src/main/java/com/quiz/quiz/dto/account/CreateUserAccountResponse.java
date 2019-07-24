package com.quiz.quiz.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain=true)
public class CreateUserAccountResponse {

    private UUID id;
}
