package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //TODO:: implement a method to aggregate balance from account transactions
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
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "account", orphanRemoval = true)
    private List<BankTransaction> transactionList = new ArrayList<>();


    public Account(Long id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }


    public Account() {
    }


    public void addTransactionToAccount(BankTransaction bankTransaction) {

        if (bankTransaction.getTransactionType() == BankTransactionType.DEPOSIT) {

            increaseBalance(bankTransaction.getAmount());

        } else if (bankTransaction.getTransactionType() == BankTransactionType.WITHDRAW) {

            if(bankTransaction.getAmount() > balance)throw new IllegalArgumentException("no enough balance to withdraw from");

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
