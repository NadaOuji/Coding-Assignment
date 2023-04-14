package com.assignment.AccountService.mapper;

import com.assignment.AccountService.dto.AccountDTO;
import com.assignment.AccountService.dto.AccountResponseDTO;
import com.assignment.AccountService.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    @Mapping(source = "customerId", target = "customerId")
    Account toEntity(AccountDTO accountDTO);
    List<Account> toEntitiesDTO(List<AccountDTO> accountDTO);

    AccountResponseDTO toDTO(Account entity);
    List<AccountResponseDTO> toAccountsDTO(List<Account> accounts);
    void updateAccountFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

}
