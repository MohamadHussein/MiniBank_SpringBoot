package com.finance.minibank.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AccountDTO {
    @JsonProperty("customerId")
    @NotNull(message = "missing field: customer id")
    private Long customerId;

    @DecimalMin(value = "0", message = "account balance can't be negative")
    private Double balance;

    public void setBalance(Double balance) {
        this.balance = balance;
    }

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
