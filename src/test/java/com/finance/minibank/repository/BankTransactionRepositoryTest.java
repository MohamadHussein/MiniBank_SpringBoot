package com.finance.minibank.repository;

import com.finance.minibank.model.Account;
import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
public class BankTransactionRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager; //used to persist data

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    private AccountRepository accountRepository; // since bankTransaction needs to be bound to an account,
    // so we create an acount


    private static final Long ACCOUNT_ID = 1L;
    private static final Double ACCOUNT_BALANCE = 120.0;


    @Before
    public void init(){
        Account account = new Account(ACCOUNT_ID,ACCOUNT_BALANCE);
        accountRepository.save(account);
    }

    @Test
    public void findNoTransactions_ifRepoIsEmpty() {
        Iterable<BankTransaction> transactions = bankTransactionRepository.findAll();
        assertThat(transactions).isEmpty();
    }


    @Test
    public void findTransactionsByAccountID(){
        Account account = accountRepository.findById(ACCOUNT_ID).get();
        BankTransaction tr1 = new BankTransaction(100.0, BankTransactionType.DEPOSIT);
        tr1.setAccount(account);
        testEntityManager.persist(tr1);
        BankTransaction tr2 = new BankTransaction(10.0, BankTransactionType.WITHDRAW);
        tr2.setAccount(account);
        testEntityManager.persist(tr2);

        BankTransaction tr3 = new BankTransaction(120.0, BankTransactionType.DEPOSIT);
        tr3.setAccount(account);
        testEntityManager.persist(tr3);

        List<BankTransaction> bankTransactionList = bankTransactionRepository.findByAccountId(1L);

        assertThat(bankTransactionList).hasSize(3).contains(tr1,tr2,tr3);
    }


}
