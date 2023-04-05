package com.assignment.TransactionService.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TransactionResponseDTOTest {

    @Test
    public void testConstructorAndGetters() {
        Long id = 1L;
        Long accountId = 2L;
        Double amount = 100.0;
        String description = "Test transaction";
        LocalDateTime date = LocalDateTime.now();
        String message = "Transaction successful";

        TransactionResponseDTO dto = new TransactionResponseDTO(id, accountId, amount, description, date, message);

        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(accountId, dto.getAccountId());
        Assertions.assertEquals(amount, dto.getAmount());
        Assertions.assertEquals(description, dto.getDescription());
        Assertions.assertEquals(date, dto.getDate());
        Assertions.assertEquals(message, dto.getMessage());
    }

    @Test
    public void testSetters() {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(1L);
        dto.setAccountId(2L);
        dto.setAmount(100.0);
        dto.setDescription("Test transaction");
        dto.setDate(LocalDateTime.now());
        dto.setMessage("Transaction successful");

        Assertions.assertEquals(1L, dto.getId());
        Assertions.assertEquals(2L, dto.getAccountId());
        Assertions.assertEquals(100.0, dto.getAmount());
        Assertions.assertEquals("Test transaction", dto.getDescription());
        Assertions.assertNotNull(dto.getDate());
        Assertions.assertEquals("Transaction successful", dto.getMessage());
    }
}
