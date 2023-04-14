package com.assignment.AccountService.service;

import com.assignment.AccountService.dto.AccountDTO;
import com.assignment.AccountService.dto.AccountResponseDTO;
import com.assignment.AccountService.mapper.AccountMapper;
import com.assignment.AccountService.model.Account;
import com.assignment.AccountService.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @BeforeEach
    public void beforeEach() {
        accountRepository.deleteAll();
    }

    @Test
    void testGetAllAccounts() {
        // given
        Account account1 = accountRepository.save(new Account(1L, new BigDecimal("100.00")));
        Account account2 = accountRepository.save(new Account(2L, new BigDecimal("200.00")));

        // when
        List<AccountResponseDTO> accountResponseDTOList = accountService.getAllAccounts();

        // then
        assertEquals(2, accountResponseDTOList.size());

        AccountResponseDTO accountResponseDTO1 = accountResponseDTOList.get(0);
        assertEquals(account1.getId(), accountResponseDTO1.getId());
        assertEquals(account1.getCustomerId(), accountResponseDTO1.getCustomerId());
        assertEquals(account1.getBalance(), accountResponseDTO1.getBalance());

        AccountResponseDTO accountResponseDTO2 = accountResponseDTOList.get(1);
        assertEquals(account2.getId(), accountResponseDTO2.getId());
        assertEquals(account2.getCustomerId(), accountResponseDTO2.getCustomerId());
        assertEquals(account2.getBalance(), accountResponseDTO2.getBalance());
    }

    @Test
    void testGetAccountById() {
        // given
        Account account = accountRepository.save(new Account(1L, new BigDecimal("100.00")));

        // when
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(account.getId());

        // then
        assertNotNull(accountResponseDTO);
        assertEquals(account.getId(), accountResponseDTO.getId());
        assertEquals(account.getCustomerId(), accountResponseDTO.getCustomerId());
        assertEquals(account.getBalance(), accountResponseDTO.getBalance());
    }

    @Test
    void testCreateAccount() {
        // given
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(1L);
        accountDTO.setBalance(new BigDecimal("100.00"));

        // when
        Account createdAccount = accountService.createAccount(accountDTO);

        // then
        assertNotNull(createdAccount.getId());
        assertEquals(accountDTO.getCustomerId(), createdAccount.getCustomerId());
        assertEquals(accountDTO.getBalance(), createdAccount.getBalance());
    }

    @Test
    void testUpdateAccount() {
        // given
        Account account = accountRepository.save(new Account(1L, new BigDecimal("100.00")));
        AccountDTO updatedAccountDTO = new AccountDTO();
        updatedAccountDTO.setId(account.getId());
        updatedAccountDTO.setCustomerId(1L);
        updatedAccountDTO.setBalance(new BigDecimal("200.00"));

        // when
        AccountResponseDTO updatedAccountResponseDTO = accountService.updateAccount(updatedAccountDTO);

        // then
        assertNotNull(updatedAccountResponseDTO);
        assertEquals(updatedAccountDTO.getId(), updatedAccountResponseDTO.getId());
        assertEquals(updatedAccountDTO.getCustomerId(), updatedAccountResponseDTO.getCustomerId());
        assertEquals(updatedAccountDTO.getBalance(), updatedAccountResponseDTO.getBalance());

        Account updatedAccount = accountRepository.findById(updatedAccountDTO.getId())
                .orElseThrow(() -> new AssertionError("Account not updated"));
        assertEquals(updatedAccountDTO.getCustomerId(), updatedAccount.getCustomerId());
        assertEquals(updatedAccountDTO.getBalance(), updatedAccount.getBalance());
    }
}
