package com.assignment.TransactionService.mapper;

import com.assignment.TransactionService.dto.TransactionDTO;
import com.assignment.TransactionService.dto.TransactionResponseDTO;
import com.assignment.TransactionService.model.Transaction;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionMapperTest {

    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    public void toDTO_shouldMapEntityToDTO() {
        // Given
        Transaction transaction = new Transaction(1L, 100L, 50.0, "Transaction Description", LocalDateTime.now());

        // When
        TransactionDTO transactionDTO = transactionMapper.toDTO(transaction);

        // Then
        assertNotNull(transactionDTO);
        assertEquals(1L, transactionDTO.getId());
        assertEquals(100L, transactionDTO.getAccountId());
        assertEquals(50.0, transactionDTO.getAmount());
        assertEquals("Transaction Description", transactionDTO.getDescription());
        assertNotNull(transactionDTO.getDate());
    }

    @Test
    public void toResponseDTO_shouldMapEntityToResponseDTO() {
        // Given
        Transaction transaction = new Transaction(1L, 100L, 50.0, "Transaction Description", LocalDateTime.now());

        // When
        TransactionResponseDTO transactionResponseDTO = transactionMapper.toResponseDTO(transaction);

        // Then
        assertNotNull(transactionResponseDTO);
        assertEquals(1L, transactionResponseDTO.getId());
        assertEquals(100L, transactionResponseDTO.getAccountId());
        assertEquals(50.0, transactionResponseDTO.getAmount());
        assertEquals("Transaction Description", transactionResponseDTO.getDescription());
        assertNotNull(transactionResponseDTO.getDate());
    }

    @Test
    public void toEntity_shouldMapDTOToEntity() {
        // Given
        TransactionDTO transactionDTO = new TransactionDTO(1L, 100L, 50.0, "Transaction Description", LocalDateTime.now());

        // When
        Transaction transaction = transactionMapper.toEntity(transactionDTO);

        // Then
        assertNotNull(transaction);
        assertEquals(1L, transaction.getId());
        assertEquals(100L, transaction.getAccountId());
        assertEquals(50.0, transaction.getAmount());
        assertEquals("Transaction Description", transaction.getDescription());
        assertNotNull(transaction.getDate());
    }

    @Test
    public void toResponseDTOList_shouldMapEntityListToResponseDTOList() {
        // Given
        List<Transaction> transactions = List.of(
                new Transaction(1L, 100L, 50.0, "Transaction Description", LocalDateTime.now()),
                new Transaction(2L, 100L, 25.0, "Transaction Description", LocalDateTime.now()),
                new Transaction(3L, 100L, 75.0, "Transaction Description", LocalDateTime.now())
        );

        // When
        List<TransactionResponseDTO> transactionResponseDTOList = transactionMapper.toResponseDTOList(transactions);

        // Then
        assertNotNull(transactionResponseDTOList);
        assertEquals(3, transactionResponseDTOList.size());
        assertEquals(1L, transactionResponseDTOList.get(0).getId());
        assertEquals(100L, transactionResponseDTOList.get(0).getAccountId());
        assertEquals(50.0, transactionResponseDTOList.get(0).getAmount());
        assertEquals("Transaction Description", transactionResponseDTOList.get(0).getDescription());
        assertNotNull(transactionResponseDTOList.get(0).getDate());
    }
}
