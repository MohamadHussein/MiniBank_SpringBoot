package com.finance.minibank;

import com.finance.minibank.model.Account;
import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionType;
import com.finance.minibank.model.Customer;
import com.finance.minibank.repository.AccountRepository;
import com.finance.minibank.repository.BankTransactionRepository;
import com.finance.minibank.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MiniBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniBankApplication.class, args);
    }
    }
