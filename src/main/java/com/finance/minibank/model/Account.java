package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.finance.minibank.exception.RejectedTransactionException;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "balance")
    private Double balance=0.0;



    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    //only used for JSON response
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;


    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "account", orphanRemoval = true)
    private List<BankTransaction> transactionList = new ArrayList<>();

    //this constructor is used for unit testing
    public Account( Long id,Long customerId, Double balance) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
    }

    public Account(Double balance) {
        this.balance = balance;
    }
    public Account(Long id, Double balance) {
        this.id =id;
        this.balance = balance;
    }
    public Account() {
    }


    public void addTransactionToAccount(BankTransaction bankTransaction) {

        if (bankTransaction.getTransactionType() == BankTransactionType.DEPOSIT) {

            increaseBalance(bankTransaction.getAmount());

        } else if (bankTransaction.getTransactionType() == BankTransactionType.WITHDRAW) {

            if(bankTransaction.getAmount() > balance)throw new RejectedTransactionException("no enough balance to withdraw from");

            decreaseBalance(bankTransaction.getAmount());

        }
        transactionList.add(bankTransaction);

    }

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

    private void increaseBalance(Double amount) {
        this.balance += amount;
    }

    private void decreaseBalance(Double amount) {
        this.balance -= amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BankTransaction> getTransactionList() {
        return this.transactionList;
    }

}
