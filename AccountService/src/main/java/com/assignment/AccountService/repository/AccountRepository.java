package com.assignment.AccountService.repository;

import com.assignment.AccountService.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing accounts.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
