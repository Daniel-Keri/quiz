package com.quiz.quiz.security;

import com.quiz.quiz.entity.Account;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import java.util.UUID;

@Data
public class CustomUserImpl extends User {

    private final UUID id;

    public CustomUserImpl(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getEmail(), account.getPassword(), authorities);

        this.id = account.getId();
    }
}
