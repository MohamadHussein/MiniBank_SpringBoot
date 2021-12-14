package com.finance.minibank.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class BankTransactionDTO {

    @DecimalMin(value = "1", message = "transaction amount has to be at least 1$")
    private Double amount;

    @NotNull(message = "missing field: transactionType: choose DEPOSIT or WITHDRAW")
    private BankTransactionType transactionType;

    @NotNull(message = "missing field: accountId")
    private Long accountId;

    public BankTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(BankTransactionType transactionType) {
        this.transactionType = transactionType;
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
}
