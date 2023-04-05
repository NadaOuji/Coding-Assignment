package com.assignment.TransactionService.dto;

import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;
    private Long accountId;
    private Double amount;
    private String description;
    private LocalDateTime date;

    public TransactionDTO(Long id, Long accountId, Double amount, String description, LocalDateTime date) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
    public TransactionDTO(Long accountId, Double amount, String description, LocalDateTime date) {
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
    public TransactionDTO() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
