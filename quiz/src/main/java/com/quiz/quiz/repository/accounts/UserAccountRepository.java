package com.quiz.quiz.repository.accounts;

import com.quiz.quiz.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    Optional<UserAccount> findByEmail(String email);

    @Query("SELECT a FROM com.quiz.quiz.entity.UserAccount a WHERE a.id = ?#{principal.id}")
    Optional<UserAccount> getUserAccountData();
}