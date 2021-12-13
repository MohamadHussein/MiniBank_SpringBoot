package com.finance.minibank.service;

import com.finance.minibank.exception.EntityNotFoundException;
import com.finance.minibank.exception.NoDataFoundException;
import com.finance.minibank.model.Account;
import com.finance.minibank.repository.AccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static final Long VALID_ACCOUNT_ID = 1L;
    private static final Long INVALID_ACCOUNT_ID = 3L;
    private static final Long VALID_CUSTOMER_ID = 2L;
    private static final Long INVALID_CUSTOMER_ID = 3L;
    private static final Double VALID_BALANCE = 1200.0;


    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private CustomerService customerService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void init(){
        Account account = new Account(VALID_ACCOUNT_ID,VALID_CUSTOMER_ID,VALID_BALANCE);
        Account account1 = new Account(2L,VALID_CUSTOMER_ID,VALID_BALANCE);

        when(accountRepository.findById(VALID_ACCOUNT_ID)).thenReturn(Optional.of(account));
        when(accountRepository.findById(INVALID_ACCOUNT_ID)).thenReturn(Optional.empty());

        when(accountRepository.findByCustomerId(VALID_CUSTOMER_ID)).thenReturn(Arrays.asList(account,account1));
        when(accountRepository.findByCustomerId(INVALID_CUSTOMER_ID)).thenReturn(Arrays.asList());


    }

    @Test
    public void getAccountById_oneAccountPresentAndOneIsMissing() {

        Account account = accountService.getAccountById(VALID_ACCOUNT_ID);
        assertThat(account.getCustomerId()).isEqualTo(VALID_CUSTOMER_ID);
        assertThat(account.getBalance()).isEqualTo(VALID_BALANCE);

        Exception thrown = assertThrows(EntityNotFoundException.class, () -> {
            accountService.getAccountById(INVALID_ACCOUNT_ID);
        });
        Assert.assertTrue(StringUtils.containsIgnoreCase(thrown.getMessage(), "account with id 3 not found"));
    }


    @Test
    public void getAccountsByCustomerId_twoAccountsArePresent_AnotherCustomerHasNoAccounts() {
        //customer with 2 accounts
        List<Account> accountList = accountService.getAccountsByCustomerId(VALID_CUSTOMER_ID);
        Assertions.assertThat(accountList).hasSize(2);

        //customer with no accounts
        Exception thrown = assertThrows(NoDataFoundException.class, () -> {
            accountService.getAccountsByCustomerId(INVALID_CUSTOMER_ID);
        });
        Assert.assertTrue(StringUtils.containsIgnoreCase(thrown.getMessage(), "no accounts were found for customer"));

    }
}
