package com.quiz.quiz.errorHandling.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomException extends Exception {

    private int code;

    private String message;
}
