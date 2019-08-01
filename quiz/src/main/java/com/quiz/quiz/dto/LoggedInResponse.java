package com.quiz.quiz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@NoArgsConstructor
public class LoggedInResponse {

    private String message;


}
