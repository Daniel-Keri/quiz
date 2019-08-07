package com.quiz.quiz.principal;

import com.quiz.quiz.security.CustomUserImpl;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class CustomPrincipal {

    public UUID getLoggedInAccountId() {

        return ((CustomUserImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}
