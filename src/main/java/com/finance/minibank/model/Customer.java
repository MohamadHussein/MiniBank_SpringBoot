package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String name;

    @NotBlank
    private String surname;


    @Formula("(SELECT SUM(A.balance) FROM ACCOUNT A WHERE A.customer_id = id)") //this field is read-only
    private Double totalBalance = 0.0;


    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
    private List<Account> accountList = new ArrayList<>();


    public Customer(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    public Customer() {

    }


    public Double getTotalBalance() {
        return totalBalance;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void addToAccountList(Account account) {
        accountList.add(account);
    }


    public List<Account> getAccountList() {
        return accountList;
    }

}

