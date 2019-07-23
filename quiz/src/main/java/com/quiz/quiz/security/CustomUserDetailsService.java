package com.quiz.quiz.security;

import com.quiz.quiz.entity.Account;
import com.quiz.quiz.enums.Role;
import com.quiz.quiz.repository.accounts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException(username));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        switch (account.getRole()) {
            case ROLE_USER:
                grantedAuthorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));
                return new CustomUserImpl(account, grantedAuthorities);
            case ROLE_ADMIN:
                grantedAuthorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
                return new CustomUserImpl(account, grantedAuthorities);
            default:
                throw new IllegalArgumentException();
        }
    }
}
