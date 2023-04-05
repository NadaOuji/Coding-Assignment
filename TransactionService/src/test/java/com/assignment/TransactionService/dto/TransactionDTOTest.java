package com.assignment.TransactionService.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TransactionDTOTest {

    @Test
    public void testTransactionDTO() {
        Long id = 1L;
        Long accountId = 2L;
        Double amount = 100.00;
        String description = "Test transaction";
        LocalDateTime date = LocalDateTime.now();

        TransactionDTO transactionDTO = new TransactionDTO(id, accountId, amount, description, date);

        Assertions.assertEquals(id, transactionDTO.getId());
        Assertions.assertEquals(accountId, transactionDTO.getAccountId());
        Assertions.assertEquals(amount, transactionDTO.getAmount());
        Assertions.assertEquals(description, transactionDTO.getDescription());
        Assertions.assertEquals(date, transactionDTO.getDate());

        Long newId = 3L;
        transactionDTO.setId(newId);
        transactionDTO.setAccountId(accountId);
        transactionDTO.setAmount(amount);
        transactionDTO.setDescription(description);
        transactionDTO.setDate(date);

        Assertions.assertEquals(newId, transactionDTO.getId());
        Assertions.assertEquals(accountId, transactionDTO.getAccountId());
        Assertions.assertEquals(amount, transactionDTO.getAmount());
        Assertions.assertEquals(description, transactionDTO.getDescription());
        Assertions.assertEquals(date, transactionDTO.getDate());
    }
}
