package com.assignment.AccountService.controller;

import com.assignment.AccountService.dto.AccountDTO;
import com.assignment.AccountService.dto.AccountResponseDTO;
import com.assignment.AccountService.exception.ResourceNotFoundException;
import com.assignment.AccountService.mapper.AccountMapper;
import com.assignment.AccountService.model.Account;
import com.assignment.AccountService.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * AccountController is responsible for handling RESTful endpoints related
 * to account operations
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    private final AccountService accountService;
    private final AccountMapper accountMapper;


    /**
     * Constructs a new AccountController with the specified accountService.
     *
     * @param accountService The accountService to use.
     */
    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    /**
     * Creates a new account.
     *
     * @param accountDTO The account data in the form of an AccountDTO object.
     * @return An AccountResponseDTO object containing the newly created account data.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(@RequestBody @Validated AccountDTO accountDTO) {
        logger.info("Creating account with details: {}", accountDTO);
        Account account = accountService.createAccount(accountDTO);
        AccountResponseDTO responseDTO = accountMapper.toDTO(account);
        logger.info("Created account with details: {}", responseDTO);
        return responseDTO;
    }

    /**
     * Retrieves a list of all accounts.
     *
     * @return A List of AccountResponseDTO objects containing the account data for all accounts.
     */
    @GetMapping
    public List<AccountResponseDTO> getAllAccounts() {
        logger.info("Request received to get all accounts.");
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        logger.info("Returning all accounts.");
        return accounts;
    }

    /**
     * Retrieves the account data for a specific account.
     *
     * @param id The ID of the account to retrieve.
     * @return An AccountResponseDTO object containing the account data for the specified account.
     * @throws ResourceNotFoundException if the specified account is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        Optional<AccountResponseDTO> accountOpt = Optional.ofNullable(accountService.getAccountById(id));
        AccountResponseDTO account = accountOpt.orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
        logger.info("Retrieved account with id {}", id);
        return ResponseEntity.ok(account);
    }


    /**
     * Updates the data for a specific account.
     *
     * @param id The ID of the account to update.
     * @param accountDTO The updated account data in the form of an AccountDTO object.
     * @return An AccountResponseDTO object containing the updated account data.
     * @throws ResourceNotFoundException if the specified account is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id, @RequestBody @Validated AccountDTO accountDTO) {
        logger.info("Updating account with id: {}", id);
        AccountResponseDTO existingAccount = accountService.getAccountById(id);
        if (existingAccount == null) {
            throw new ResourceNotFoundException("Account not found with id " + id);
        }
        Account account = accountMapper.toEntity(accountDTO);
        account.setId(id);
        accountMapper.updateAccountFromDTO(accountDTO, account);
        AccountResponseDTO updatedAccount = accountService.updateAccount(accountDTO);
        logger.info("Account with id {} updated successfully", id);
        return ResponseEntity.ok(updatedAccount);
    }

}
