package com.finance.minibank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;
    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
    private List<Account> accountList = new ArrayList<>();

    public Long getId() {
        return id;
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

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addToAccountList(Account account){
        accountList.add(account);
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }


    public Customer(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;


    }

    public Customer() {

    }
}
