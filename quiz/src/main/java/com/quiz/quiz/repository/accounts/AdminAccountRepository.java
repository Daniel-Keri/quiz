package com.quiz.quiz.repository.accounts;

import com.quiz.quiz.entity.Account;
import com.quiz.quiz.entity.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, UUID> {
    Optional<AdminAccount> findByEmail(String email);

    @Query("SELECT a FROM com.quiz.quiz.entity.AdminAccount a WHERE a.id = ?#{principal.id}")
    Optional<AdminAccount> getAdminAccountData();
}
