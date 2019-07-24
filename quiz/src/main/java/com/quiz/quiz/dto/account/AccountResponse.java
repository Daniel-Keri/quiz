package com.quiz.quiz.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
@Accessors(chain=true)
public class AccountResponse {

    private UUID id;
}
