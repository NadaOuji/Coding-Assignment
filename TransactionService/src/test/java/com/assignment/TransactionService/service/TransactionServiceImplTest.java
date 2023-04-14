package com.assignment.TransactionService.service;

import com.assignment.TransactionService.dto.TransactionDTO;
import com.assignment.TransactionService.dto.TransactionResponseDTO;
import com.assignment.TransactionService.mapper.TransactionMapper;
import com.assignment.TransactionService.model.Transaction;
import com.assignment.TransactionService.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private TransactionMapper transactionMapper;

    @Test
    void addTransaction_shouldReturnTransactionDTO() {
        // Given
        TransactionDTO transactionDTO = new TransactionDTO(100L, 50.0, "Transaction Description", LocalDateTime.now());
        Transaction transaction = new Transaction(1L, 100L, 50.0, "Transaction Description", LocalDateTime.now());
        Transaction savedTransaction = new Transaction(1L, 100L, 50.0, "Transaction Description", LocalDateTime.now());
        TransactionDTO expectedTransactionDTO = new TransactionDTO(1L, 100L, 50.0, "Transaction Description", LocalDateTime.now());

        doReturn(transaction).when(transactionMapper).toEntity(transactionDTO);
        doReturn(savedTransaction).when(transactionRepository).save(transaction);
        doReturn(expectedTransactionDTO).when(transactionMapper).toDTO(savedTransaction);

        // When
        TransactionDTO result = transactionService.addTransaction(transactionDTO);

        // Then
        assertEquals(expectedTransactionDTO, result);
    }

    @Test
    void getTransactionById_shouldReturnTransactionResponseDTO() {
        // Given
        Long transactionId = 1L;
        LocalDateTime now = LocalDateTime.now();
        Transaction transaction = new Transaction(1L, 100L, 50.0, "Transaction Description", now);
        TransactionResponseDTO expectedTransactionResponseDTO = new TransactionResponseDTO(1L, 100L, 50.0,"Transaction Description", now,"Transaction Description");
        expectedTransactionResponseDTO.setMessage("Transaction found!");

        doReturn(java.util.Optional.of(transaction)).when(transactionRepository).findById(transactionId);
        doReturn(expectedTransactionResponseDTO).when(transactionMapper).toResponseDTO(transaction);

        // When
        TransactionResponseDTO result = transactionService.getTransactionById(transactionId);

        // Then
        assertEquals(expectedTransactionResponseDTO, result);
    }

}
