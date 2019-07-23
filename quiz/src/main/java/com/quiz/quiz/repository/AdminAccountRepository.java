package com.quiz.quiz.repository;

import com.quiz.quiz.entity.Account;
import com.quiz.quiz.entity.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, UUID> {
    Optional<AdminAccount> findByEmail(String email);
}
