package com.assignment.TransactionService.controller;

import com.assignment.TransactionService.dto.TransactionDTO;
import com.assignment.TransactionService.dto.TransactionResponseDTO;
import com.assignment.TransactionService.model.Transaction;
import com.assignment.TransactionService.repository.TransactionRepository;
import com.assignment.TransactionService.service.TransactionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository.deleteAll();
    }
    @Test
    public void testGetTransactionsByAccountId() throws Exception {
        // Given
        Long accountId = 1L;
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, accountId, 1000.0, "Test transaction 1", LocalDateTime.now()),
                new Transaction(2L, accountId, 2000.0, "Test transaction 2", LocalDateTime.now())
        );
        transactionRepository.saveAll(transactions);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/transactions/" + accountId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // Then
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());

        String contentAsString = response.getContentAsString();
        List<TransactionResponseDTO> responseDTOs = objectMapper.readValue(contentAsString, new TypeReference<List<TransactionResponseDTO>>() {
        });
        assertEquals(responseDTOs.size(), 2);
    }

    @Test
    public void testAddTransaction() throws Exception {
        // Given
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(1L);
        transactionDTO.setDescription("Test transaction");
        transactionDTO.setAmount(10.0);
        transactionDTO.setDate(LocalDateTime.now());

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDTO))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andReturn();

        // Then
        String content = result.getResponse().getContentAsString();
        TransactionDTO createdTransaction = objectMapper.readValue(content, TransactionDTO.class);
        assertNotNull(createdTransaction.getId());
        assertEquals(transactionDTO.getAccountId(), createdTransaction.getAccountId());
        assertEquals(transactionDTO.getDescription(), createdTransaction.getDescription());
        assertEquals(transactionDTO.getAmount(), createdTransaction.getAmount());
        assertEquals(transactionDTO.getDate(), createdTransaction.getDate());

        Transaction savedTransaction = transactionRepository.findById(createdTransaction.getId()).orElse(null);
        assertNotNull(savedTransaction);
        assertEquals(createdTransaction.getAccountId(), savedTransaction.getAccountId());
        assertEquals(createdTransaction.getDescription(), savedTransaction.getDescription());
        assertEquals(createdTransaction.getAmount(), savedTransaction.getAmount());
        assertEquals(createdTransaction.getDate().toLocalDate(), savedTransaction.getDate().toLocalDate());
    }
}
