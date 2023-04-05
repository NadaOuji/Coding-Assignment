package com.assignment.TransactionService.repository;

import com.assignment.TransactionService.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Should find transactions by accountId")
    void testFindByAccountId() {
        // Given
        Long accountId = 1L;
        LocalDateTime now = LocalDateTime.now();
        Transaction transaction1 = new Transaction(accountId, 100.0, "Transaction 1", now);
        Transaction transaction2 = new Transaction(accountId, 200.0, "Transaction 2", now);
        Transaction transaction3 = new Transaction(2L, 300.0, "Transaction 3", now);

        transactionRepository.saveAll(List.of(transaction1, transaction2, transaction3));

        // When
        List<Transaction> foundTransactions = transactionRepository.findByAccountId(accountId);

        // Then
        assertEquals(foundTransactions.size(), 2);
        assertEquals(foundTransactions.get(0).getAccountId(), transaction1.getAccountId());
        assertEquals(foundTransactions.get(0).getAmount(), transaction1.getAmount());
        assertEquals(foundTransactions.get(0).getDescription(), transaction1.getDescription());
        assertEquals(foundTransactions.get(1).getAccountId(), transaction2.getAccountId());
        assertEquals(foundTransactions.get(1).getAmount(), transaction2.getAmount());
        assertEquals(foundTransactions.get(1).getDescription(), transaction2.getDescription());
    }

}
