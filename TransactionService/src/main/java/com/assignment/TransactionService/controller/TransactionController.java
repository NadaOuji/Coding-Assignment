package com.assignment.TransactionService.controller;

import com.assignment.TransactionService.dto.TransactionDTO;
import com.assignment.TransactionService.dto.TransactionResponseDTO;
import com.assignment.TransactionService.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


/**
 * TransactionController is responsible for handling RESTful endpoints related
 * to transaction operations
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    /**
     * Constructs a new TransactionController with the specified TransactionService.
     *
     * @param transactionService The TransactionService to use.
     */
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Retrieves a list of all transactions for the specified account.
     *
     * @param accountId The ID of the account to retrieve transactions for.
     * @return A ResponseEntity containing a List of TransactionDTO objects containing the transaction data for the specified account,
     *         or a NOT_FOUND response if the specified account does not exist.
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        logger.info("Getting transactions for account with ID: {}", accountId);
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByAccountId(accountId);
        return Optional.ofNullable(transactions)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found for account with ID: " + accountId));
    }



    /**
     * Adds a new transaction.
     *
     * @param transactionDTO The transaction data in the form of a TransactionDTO object.
     * @return A ResponseEntity containing a TransactionDTO object containing the newly created transaction data,
     *         or a BAD_REQUEST response if the transaction data is invalid.
     */
    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        logger.info("Received request to add new transaction: {}", transactionDTO);
        TransactionDTO createdTransaction = transactionService.addTransaction(transactionDTO);
        return Optional.ofNullable(createdTransaction)
                .map(t -> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
