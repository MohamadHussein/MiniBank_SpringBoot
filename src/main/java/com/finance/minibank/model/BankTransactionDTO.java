package com.finance.minibank.model;

public class BankTransactionDTO {
    private Double amount;

    private BankTransactionType transactionType;
    private Long accountID;

    public BankTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(BankTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
