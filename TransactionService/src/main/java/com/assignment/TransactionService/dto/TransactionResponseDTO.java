package com.assignment.TransactionService.dto;

import java.time.LocalDateTime;

public class TransactionResponseDTO {
    private Long id;
    private Long accountId;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private String message;

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(Long id, Long accountId, Double amount, String description, LocalDateTime date, String message) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
