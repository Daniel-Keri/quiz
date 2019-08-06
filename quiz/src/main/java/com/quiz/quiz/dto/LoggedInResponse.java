package com.quiz.quiz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain=true)
@NoArgsConstructor
public class LoggedInResponse {

    private Boolean success;

    private String message;
}
