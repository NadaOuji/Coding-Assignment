package com.assignment.TransactionService.service;

import com.assignment.TransactionService.dto.TransactionDTO;
import com.assignment.TransactionService.dto.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO addTransaction(TransactionDTO transactionDTO);

    TransactionResponseDTO getTransactionById(Long transactionId);

    List<TransactionResponseDTO> getTransactionsByAccountId(Long accountId);
}
