package com.assignment.AccountService.service;

import com.assignment.AccountService.dto.AccountDTO;
import com.assignment.AccountService.dto.AccountResponseDTO;
import com.assignment.AccountService.mapper.AccountMapper;
import com.assignment.AccountService.model.Account;
import com.assignment.AccountService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing accounts.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    /**
     * Create a new account.
     *
     * @param accountDTO the account DTO to create
     * @return the created account
     */
    public Account createAccount(AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);
        return accountRepository.save(account);
    }

    /**
     * Get all accounts.
     *
     * @return a list of all account DTOs
     */
    public List<AccountResponseDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toAccountsDTO(accounts);
    }

    /**
     * Get an account by ID.
     *
     * @param id the ID of the account to get
     * @return the account DTO with the given ID, or null if not found
     */
    public AccountResponseDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDTO)
                .orElse(null);
    }

    /**
     * Update an existing account.
     *
     * @param accountDTO the account DTO to update
     * @return the updated account DTO, or null if the account was not found
     */
    public AccountResponseDTO updateAccount(AccountDTO accountDTO) {
        Account accountFromDTO = accountMapper.toEntity(accountDTO);
        return accountRepository.findById(accountFromDTO.getId())
                .map(account -> {
                    Account updatedAccount = accountMapper.toEntity(accountDTO);
                    updatedAccount.setId(account.getId());
                    Account savedAccount = accountRepository.save(updatedAccount);
                    return accountMapper.toDTO(savedAccount);
                })
                .orElse(null);
    }

}
