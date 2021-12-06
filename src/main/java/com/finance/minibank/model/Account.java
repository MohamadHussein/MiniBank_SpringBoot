package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

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

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "account",orphanRemoval = true)
    private List<BankTransaction> transactionList = new ArrayList<>();

    public void  addTransactionToAccount(BankTransaction bankTransaction){
        transactionList.add(bankTransaction);
    }

    public Account(Long id, Double balance, Customer customer, List<BankTransaction> transactionList) {
        this.id = id;
        this.balance = balance;
        this.customer = customer;
        this.transactionList = transactionList;
    }



    public Account() {

    }
//
//
//    public List<BankTransaction> getTransactionList() {
//        return transactionList;
//    }
//
//    public void setTransactionList(List<BankTransaction> transactionList) {
//        this.transactionList = transactionList;
//    }
}
