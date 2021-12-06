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
    @Bean
    CommandLineRunner commandLineRunner(BankTransactionRepository transactionRepository, AccountRepository accountRepository, CustomerRepository customerRepository) {
        return args -> {
            Customer c1 = new Customer(
                    1L,
                    "Mohammad",
                    "mohomar"

            );
            Customer c2 = new Customer(
                    2L,
                    "Omar",
                    "Ibra"

            );
            customerRepository.saveAll(Arrays.asList(c1,c2));
            BankTransaction tr1 = new BankTransaction(
                    1L,
                    100.0, BankTransactionType.DEPOSIT

            );
            BankTransaction tr2 = new BankTransaction(
                    2L,
                    200.0, BankTransactionType.WITHDRAW

            );
            BankTransaction tr3 = new BankTransaction(
                    3L,
                    300.0,BankTransactionType.DEPOSIT

            );
            BankTransaction tr4 = new BankTransaction(
                    4L,
                    400.0, BankTransactionType.WITHDRAW

            );
            transactionRepository.saveAll(Arrays.asList(tr1,tr2,tr3,tr4));
            Account acc1 = new Account(
                    1L,
                    500.0,
                    c1
                    ,Arrays.asList(tr1,tr2)

            );
            Account acc2 = new Account(
                    2L,
                    2500.0,
                    c2
                    ,Arrays.asList(tr3,tr4)

            );
            accountRepository.saveAll(Arrays.asList(acc1,acc2));
            tr1.setAccount(acc1);
            tr2.setAccount(acc1);
            tr3.setAccount(acc2);
            tr4.setAccount(acc2);
            transactionRepository.saveAll(Arrays.asList(tr1,tr2,tr3,tr4));



        };
    }
}