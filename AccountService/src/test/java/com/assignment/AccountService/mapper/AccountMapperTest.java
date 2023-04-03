package com.assignment.AccountService.mapper;

import com.assignment.AccountService.dto.AccountDTO;
import com.assignment.AccountService.dto.AccountResponseDTO;
import com.assignment.AccountService.model.Account;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountMapperTest {

    private final AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

    @Test
    public void testToEntity() {
        // given
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(1L);
        accountDTO.setBalance(new BigDecimal("100.00"));

        // when
        Account account = mapper.toEntity(accountDTO);

        // then
        assertEquals(accountDTO.getCustomerId(), account.getCustomerId());
        assertEquals(accountDTO.getBalance(), account.getBalance());
    }

    @Test
    public void testToDTO() {
        // given
        Account account = new Account();
        account.setId(1L);
        account.setCustomerId(1L);
        account.setBalance(new BigDecimal("100.00"));

        // when
        AccountResponseDTO accountResponseDTO = mapper.toDTO(account);

        // then
        assertEquals(account.getId(), accountResponseDTO.getId());
        assertEquals(account.getCustomerId(), accountResponseDTO.getCustomerId());
        assertEquals(account.getBalance(), accountResponseDTO.getBalance());
    }

    @Test
    public void testToAccountsDTO() {
        // given
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account();
        account1.setId(1L);
        account1.setCustomerId(1L);
        account1.setBalance(new BigDecimal("100.00"));
        accounts.add(account1);

        Account account2 = new Account();
        account2.setId(2L);
        account2.setCustomerId(2L);
        account2.setBalance(new BigDecimal("200.00"));
        accounts.add(account2);

        // when
        List<AccountResponseDTO> accountResponseDTOList = mapper.toAccountsDTO(accounts);

        // then
        assertEquals(accounts.size(), accountResponseDTOList.size());
        assertEquals(accounts.get(0).getId(), accountResponseDTOList.get(0).getId());
        assertEquals(accounts.get(0).getCustomerId(), accountResponseDTOList.get(0).getCustomerId());
        assertEquals(accounts.get(0).getBalance(), accountResponseDTOList.get(0).getBalance());

        assertEquals(accounts.get(1).getId(), accountResponseDTOList.get(1).getId());
        assertEquals(accounts.get(1).getCustomerId(), accountResponseDTOList.get(1).getCustomerId());
        assertEquals(accounts.get(1).getBalance(), accountResponseDTOList.get(1).getBalance());
    }

}
