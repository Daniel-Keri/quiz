package com.quiz.quiz.controller;

import com.quiz.quiz.dto.LoggedInResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class LoginController {

    @PostMapping("/loginSuccessful")
    @ResponseStatus(HttpStatus.OK)
    public LoggedInResponse loginSuccessful() {

        return new LoggedInResponse()
                .setSuccess(true)
                .setMessage("Logged in");
    }

    @PostMapping("/loginFailed")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public LoggedInResponse loginFailed() {

        return new LoggedInResponse()
                .setSuccess(false)
                .setMessage("Not logged in");
    }
}
