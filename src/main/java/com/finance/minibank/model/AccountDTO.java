package com.finance.minibank.model;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AccountDTO {

    @NotNull(message = "customer id field is mandatory")
    private Long customerId;

    @DecimalMin(value = "0", message = "account balance can't be less than zero")
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


}
