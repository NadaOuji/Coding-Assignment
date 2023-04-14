package com.assignment.AccountService.repository;

import com.assignment.AccountService.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void givenNewAccount_whenSave_thenReturnsSavedAccount() {
        // Given
        Account account = new Account(1L, new BigDecimal("100.00"));

        // When
        Account savedAccount = accountRepository.save(account);

        // Then
        assertNotNull(savedAccount.getId());
        assertEquals(account.getCustomerId(), savedAccount.getCustomerId());
        assertEquals(account.getBalance(), savedAccount.getBalance());
    }

    @Test
    void givenExistingAccountId_whenFindById_thenReturnsAccount() {
        // Given
        Account account = new Account(1L, new BigDecimal("100.00"));
        Account savedAccount = accountRepository.save(account);

        // When
        Optional<Account> foundAccount = accountRepository.findById(savedAccount.getId());

        // Then
        assertTrue(foundAccount.isPresent());
        assertEquals(savedAccount.getId(), foundAccount.get().getId());
        assertEquals(account.getCustomerId(), foundAccount.get().getCustomerId());
        assertEquals(account.getBalance(), foundAccount.get().getBalance());
    }

    @Test
    void givenAccountId_whenDelete_thenAccountIsDeleted() {
        // Given
        Account account = new Account(1L, new BigDecimal("100.00"));
        Account savedAccount = accountRepository.save(account);

        // When
        accountRepository.deleteById(savedAccount.getId());

        // Then
        Optional<Account> foundAccount = accountRepository.findById(savedAccount.getId());
        assertFalse(foundAccount.isPresent());
    }
}
