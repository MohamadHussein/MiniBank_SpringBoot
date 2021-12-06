package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Enumerated(EnumType.STRING)
    private BankTransactionType transactionType;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BankTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(BankTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BankTransaction(Long id, Double amount, BankTransactionType bankTransactionType) {
        this.id = id;
        this.transactionType =bankTransactionType;
//        this.account= account;
        this.amount = amount;
    }
    public BankTransaction() {
    }



}
