package com.assignment.AccountService.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountDTOTest {

    @Test
    public void testSetAndGetId() {
        // Given
        AccountDTO accountDTO = new AccountDTO();
        Long id = 1L;

        // When
        accountDTO.setId(id);
        Long returnedId = accountDTO.getId();

        // Then
        assertEquals(id, returnedId);
    }

    @Test
    public void testSetAndGetCustomerId() {
        // Given
        AccountDTO accountDTO = new AccountDTO();
        Long customerId = 2L;

        // When
        accountDTO.setCustomerId(customerId);
        Long returnedCustomerId = accountDTO.getCustomerId();

        // Then
        assertEquals(customerId, returnedCustomerId);
    }

    @Test
    public void testSetAndGetBalance() {
        // Given
        AccountDTO accountDTO = new AccountDTO();
        BigDecimal balance = new BigDecimal("100.00");

        // When
        accountDTO.setBalance(balance);
        BigDecimal returnedBalance = accountDTO.getBalance();

        // Then
        assertEquals(balance, returnedBalance);
    }
}
