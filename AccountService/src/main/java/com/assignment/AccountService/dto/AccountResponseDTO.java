package com.assignment.AccountService.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private Long customerId;
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof AccountResponseDTO)) return false;
        var other = (AccountResponseDTO) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(customerId, other.customerId) &&
                Objects.equals(balance, other.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, balance);
    }
}