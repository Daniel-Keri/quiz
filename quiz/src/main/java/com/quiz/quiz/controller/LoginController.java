package com.quiz.quiz.controller;

import com.quiz.quiz.dto.LoggedInResponse;
import com.quiz.quiz.dto.LoginFailedResponse;
import com.quiz.quiz.services.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/")
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
