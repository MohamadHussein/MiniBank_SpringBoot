package com.finance.minibank.utils;

import com.finance.minibank.model.*;
import com.finance.minibank.service.AccountService;
import com.finance.minibank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DtoUtils {

    private static AccountService accountService;
    private  static CustomerService customerService;

    private final AccountService tmpAccountService;

    private final CustomerService tmpCustomerService;

    public DtoUtils(AccountService tmpAccountService, CustomerService tmpCustomerService) {
        this.tmpAccountService = tmpAccountService;
        this.tmpCustomerService = tmpCustomerService;
    }

    @PostConstruct
    public void init() {
        accountService = tmpAccountService;
        customerService = tmpCustomerService;
    }


    public static Customer DTOtoCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        return customer;
    }

    public static Account DTOtoAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setCustomer(customerService.getCustomerById(accountDTO.getCustomerID()));
        return account;
    }

    public static BankTransaction DTOtoBankTransaction(BankTransactionDTO bankTransactionDTO) {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setAmount(bankTransactionDTO.getAmount());
        bankTransaction.setTransactionType(bankTransactionDTO.getTransactionType());
        if (bankTransactionDTO.getAccountId() != null) {
            bankTransaction.setAccount(accountService.getAccountById(bankTransactionDTO.getAccountId()));
        }
        return bankTransaction;
    }
}
