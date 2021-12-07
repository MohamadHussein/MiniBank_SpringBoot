package com.finance.minibank.model;


public class AccountDTO {
    private Long customerId;
    private Double balance;

    public Long getCustomerID() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
