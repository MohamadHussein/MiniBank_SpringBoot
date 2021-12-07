package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance = 0.0;

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

    //only used for JSON response
    @JsonProperty
    @Column(name="customer_id",insertable = false,updatable = false)
    private Long customerId;

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "account",orphanRemoval = true)
    private List<BankTransaction> transactionList = new ArrayList<>();

    public Account(Long id, Double balance, Customer customer, List<BankTransaction> transactionList) {
        this.id = id;
        this.balance = balance;
        this.customer = customer;
        this.transactionList = transactionList;
    }

    public Account() {

    }

    public void  addTransactionToAccount(BankTransaction bankTransaction){
        transactionList.add(bankTransaction);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BankTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<BankTransaction> transactionList) {
        this.transactionList = transactionList;
    }
}
