package com.assignment.AccountService.controller;

import com.assignment.AccountService.dto.AccountDTO;
import com.assignment.AccountService.dto.AccountResponseDTO;
import com.assignment.AccountService.mapper.AccountMapper;
import com.assignment.AccountService.model.Account;
import com.assignment.AccountService.repository.AccountRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;
    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    void createAccount() throws Exception {
        // given
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(1L);
        accountDTO.setBalance(new BigDecimal("100.00"));

        // when
        MvcResult mvcResult = mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDTO))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andReturn();

        // then
        AccountResponseDTO createdAccountResponseDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AccountResponseDTO.class);
        Account createdAccount = accountRepository.findById(createdAccountResponseDTO.getId())
                .orElseThrow(() -> new AssertionError("Account not created"));
        assertEquals(accountDTO.getCustomerId(), createdAccount.getCustomerId());
        assertEquals(accountDTO.getBalance(), createdAccount.getBalance());
    }

    @Test
    void getAllAccounts() throws Exception {
        // given
        Account account1 = new Account();
        account1.setCustomerId(1L);
        account1.setBalance(new BigDecimal("100.00"));
        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setCustomerId(2L);
        account2.setBalance(new BigDecimal("200.00"));
        accountRepository.save(account2);

        // when
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        List<AccountResponseDTO> accountResponseDTOList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<AccountResponseDTO>>() {
        });
        assertEquals(2, accountResponseDTOList.size());
        assertEquals(accountMapper.toDTO(account1), accountResponseDTOList.get(0));
        assertEquals(accountMapper.toDTO(account2), accountResponseDTOList.get(1));
    }

    @Test
    void getAccountById() throws Exception {
        // given
        Account account = new Account();
        account.setCustomerId(1L);
        account.setBalance(new BigDecimal("100.00"));
        accountRepository.save(account);

        // when
        MvcResult mvcResult = mockMvc.perform(get("/accounts/{id}", account.getId()))
                .andExpect(status().isOk())
                .andReturn();

        // then
        AccountResponseDTO accountResponseDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AccountResponseDTO.class);
        assertEquals(accountMapper.toDTO(account), accountResponseDTO);
    }

    @Test
    void updateAccount() throws Exception {
        // given
        Account account = new Account();
        account.setCustomerId(1L);
        account.setBalance(new BigDecimal("100.00"));
        accountRepository.save(account);

        AccountDTO updatedAccountDTO = new AccountDTO();
        updatedAccountDTO.setId(account.getId());
        updatedAccountDTO.setCustomerId(2L);
        updatedAccountDTO.setBalance(new BigDecimal("200.00"));

        // when
        MvcResult mvcResult = mockMvc.perform(put("/accounts/{id}", account.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAccountDTO))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andReturn();

        // then
        AccountResponseDTO updatedAccountResponseDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                AccountResponseDTO.class);
        assertNotNull(updatedAccountResponseDTO);
        assertEquals(account.getId(), updatedAccountResponseDTO.getId());
        assertEquals(updatedAccountDTO.getCustomerId(), updatedAccountResponseDTO.getCustomerId());
        assertEquals(updatedAccountDTO.getBalance(), updatedAccountResponseDTO.getBalance());

        Account updatedAccount = accountRepository.findById(account.getId()).orElse(null);
        assertNotNull(updatedAccount);
        assertEquals(updatedAccountDTO.getCustomerId(), updatedAccount.getCustomerId());
        assertEquals(updatedAccountDTO.getBalance(), updatedAccount.getBalance());
    }
}