package com.quiz.quiz.repository.accounts;

import com.quiz.quiz.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM com.quiz.quiz.entity.Account a WHERE a.email = ?1")
    Optional<Account> findByEmail(String email);

    @Query("SELECT a.id FROM com.quiz.quiz.entity.Account a WHERE a.id = ?#{principal.id}")
    Optional<UUID> getCurrentAccountId();
}
