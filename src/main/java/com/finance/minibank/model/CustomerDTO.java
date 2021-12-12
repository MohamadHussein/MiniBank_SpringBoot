package com.finance.minibank.model;

import javax.validation.constraints.NotBlank;

public class CustomerDTO {

    @NotBlank(message = "customer name must not be empty")
    private String name;
    @NotBlank(message = "customer surname must not be empty")
    private String surname;

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
}
