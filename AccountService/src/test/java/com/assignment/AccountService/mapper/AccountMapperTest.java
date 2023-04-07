package com.assignment.AccountService.mapper;

import com.assignment.AccountService.dto.AccountDTO;
import com.assignment.AccountService.dto.AccountResponseDTO;
import com.assignment.AccountService.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    private AccountDTO accountDTO;
    private Account account;
    private List<Account> accounts;

    @BeforeEach
    public void setUp() {
        accountDTO = new AccountDTO();
        accountDTO.setCustomerId(1L);
        accountDTO.setBalance(new BigDecimal("100.00"));
        account = createAccount(1L, 1L, new BigDecimal("100.00"));
        accounts = new ArrayList<>();
        accounts.add(createAccount(1L, 1L, new BigDecimal("100.00")));
        accounts.add(createAccount(2L, 2L, new BigDecimal("200.00")));
    }

    @Test
    @DisplayName("Test mapping from AccountDTO to Account entity")
    public void testToEntity() {
        // when
        Account account = accountMapper.toEntity(accountDTO);

        // then
        assertEquals(accountDTO.getCustomerId(), account.getCustomerId());
        assertEquals(accountDTO.getBalance(), account.getBalance());
    }

    @Test
    @DisplayName("Test mapping from Account entity to AccountResponseDTO")
    public void testToDTO() {
        // when
        AccountResponseDTO accountResponseDTO = accountMapper.toDTO(account);

        // then
        assertEquals(account.getId(), accountResponseDTO.getId());
        assertEquals(account.getCustomerId(), accountResponseDTO.getCustomerId());
        assertEquals(account.getBalance(), accountResponseDTO.getBalance());
    }

    @Test
    @DisplayName("Test mapping from List<Account> to List<AccountResponseDTO>")
    public void testToAccountsDTO() {
        // when
        List<AccountResponseDTO> accountResponseDTOList = accountMapper.toAccountsDTO(accounts);

        // then
        assertEquals(accounts.size(), accountResponseDTOList.size());
        assertEquals(accounts.get(0).getId(), accountResponseDTOList.get(0).getId());
        assertEquals(accounts.get(0).getCustomerId(), accountResponseDTOList.get(0).getCustomerId());
        assertEquals(accounts.get(0).getBalance(), accountResponseDTOList.get(0).getBalance());

        assertEquals(accounts.get(1).getId(), accountResponseDTOList.get(1).getId());
        assertEquals(accounts.get(1).getCustomerId(), accountResponseDTOList.get(1).getCustomerId());
        assertEquals(accounts.get(1).getBalance(), accountResponseDTOList.get(1).getBalance());
    }

    private Account createAccount(Long id, Long customerId, BigDecimal balance) {
        Account account = new Account();
        account.setId(id);
        account.setCustomerId(customerId);
        account.setBalance(balance);
        return account;
    }

}
