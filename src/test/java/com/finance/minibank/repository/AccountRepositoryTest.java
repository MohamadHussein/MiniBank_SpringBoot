package com.finance.minibank.repository;


import com.finance.minibank.model.Account;
import com.finance.minibank.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)

@DataJpaTest

public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository ;


    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "jack sparrow";
    private static final String CUSTOMER_SURNAME = "sparrow";
    private static final Double ACCOUNT_BALANCE = 136.0;
    @Before
    public void init(){
        Customer customer = new Customer(CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_SURNAME);
        customerRepository.save(customer);
    }
    @After
    public void cleanUp(){
        customerRepository.deleteAll();
    }

    @Test
    public void findAccountById_accountIsPresent(){

        Customer customer = customerRepository.findById(CUSTOMER_ID).get();
        Account account = new Account(ACCOUNT_BALANCE);
        account.setCustomer(customer);
        testEntityManager.persistAndFlush(account);

        Optional<Account> accountOptional = accountRepository.findById(account.getId());
        assertTrue(accountOptional.isPresent());
        Account foundaccount = accountOptional.get();
        assertThat(foundaccount.getBalance()).isEqualTo(ACCOUNT_BALANCE);

    }

    @Test
    public void findAccountsByCustomerID_accountsArePresent(){

        Customer customer = customerRepository.findById(CUSTOMER_ID).get();
        Account account = new Account(ACCOUNT_BALANCE);
        account.setCustomer(customer);
        testEntityManager.persistAndFlush(account);
        Account account2 = new Account(ACCOUNT_BALANCE + 10.0);
        account2.setCustomer(customer);
        testEntityManager.persistAndFlush(account2);

        List<Account> accountList = accountRepository.findByCustomerId(CUSTOMER_ID);

        assertThat(accountList).hasSize(2);
        assertThat(accountList).contains(account);
    }

}
