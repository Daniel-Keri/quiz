package com.quiz.quiz.config.securityConfig;

import com.quiz.quiz.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import static com.quiz.quiz.config.constants.URIConstants.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement()
                .maximumSessions(3)
                .and()
                .invalidSessionUrl("/invalidSession.html")
                .and()
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                //.antMatchers(ADMIN_ACCOUNT + EVERY_SUBPATH).authenticated()
                .antMatchers(GET, USER_ACCOUNT + EVERY_SUBPATH).authenticated()
                .antMatchers(PATCH, USER_ACCOUNT + EVERY_SUBPATH).authenticated()
                .antMatchers(QUESTION + EVERY_SUBPATH).authenticated()
                .antMatchers(SCOREBOARD + EVERY_SUBPATH).authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
//                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT));

    }

//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity
//                .ignoring()
//                //.antMatchers(POST, "/userAccounts")
//                .antMatchers(POST, "/adminAccounts");
//    }
}
