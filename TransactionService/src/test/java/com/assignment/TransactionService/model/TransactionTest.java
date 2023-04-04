package com.assignment.TransactionService.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TransactionTest {

    @Test
    public void testTransaction() {
        Long accountId = 12345L;
        Double amount = 1000.0;
        String description = "Test transaction";
        LocalDateTime date = LocalDateTime.now();

        Transaction transaction = new Transaction(accountId, amount, description, date);

        Assertions.assertEquals(accountId, transaction.getAccountId());
        Assertions.assertEquals(amount, transaction.getAmount());
        Assertions.assertEquals(description, transaction.getDescription());
        Assertions.assertEquals(date, transaction.getDate());
    }
}
