package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "1", message = "transaction amount has to be at least 1$")
    @Column(name = "amount", nullable = false)
    private Double amount;


    //for json response only
    @Column(name="account_id",insertable = false,updatable = false)
    private Long accountId;


    @JsonBackReference
    @ManyToOne(
            optional = false,
             fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    @NotNull(message = "transactionType field is mandatory: choose DEPOSIT or WITHDRAW")
    @Enumerated(EnumType.STRING)
    private BankTransactionType transactionType;


    public BankTransaction(Long id, Double amount, BankTransactionType bankTransactionType) {
        this.id = id;
        this.transactionType =bankTransactionType;
        this.amount = amount;
    }


    public BankTransaction() {
    }


    public Long getAccountId() {
        return accountId;
    }


    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


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



}
