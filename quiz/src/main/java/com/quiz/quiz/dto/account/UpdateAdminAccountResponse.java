package com.quiz.quiz.dto.account;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class UpdateAdminAccountResponse {

    private String username;
}
