package com.assignment.AccountService.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTest {
  @Test
  public void testAccount() {
      Account account = new Account();
      account.setId(1L);
      account.setCustomerId(100L);
      account.setBalance(new BigDecimal("1000.00"));

      Assertions.assertEquals(1L, account.getId());
      Assertions.assertEquals(100L, account.getCustomerId());
      Assertions.assertEquals(new BigDecimal("1000.00"), account.getBalance());
  }

}
