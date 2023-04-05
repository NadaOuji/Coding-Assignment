package com.assignment.TransactionService.mapper;

import com.assignment.TransactionService.dto.TransactionDTO;
import com.assignment.TransactionService.dto.TransactionResponseDTO;
import com.assignment.TransactionService.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO toDTO(Transaction transaction);
    TransactionResponseDTO toResponseDTO(Transaction transaction);
    Transaction toEntity(TransactionDTO transactionDTO);
    List<TransactionResponseDTO> toResponseDTOList(List<Transaction> transactions);

}