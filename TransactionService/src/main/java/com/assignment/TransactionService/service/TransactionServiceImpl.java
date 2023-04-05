package com.assignment.TransactionService.service;

import com.assignment.AccountService.exception.TransactionNotFoundException;
import com.assignment.TransactionService.dto.TransactionDTO;
import com.assignment.TransactionService.dto.TransactionResponseDTO;
import com.assignment.TransactionService.mapper.TransactionMapper;
import com.assignment.TransactionService.model.Transaction;
import com.assignment.TransactionService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(savedTransaction);
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(transaction -> {
                    TransactionResponseDTO transactionResponseDTO = transactionMapper.toResponseDTO(transaction);
                    transactionResponseDTO.setMessage("Transaction found!");
                    return transactionResponseDTO;
                })
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + transactionId));
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for account ID: " + accountId);
        }
        return transactionMapper.toResponseDTOList(transactions);
    }
}
