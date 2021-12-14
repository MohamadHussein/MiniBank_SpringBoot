package com.finance.minibank.service;

import com.finance.minibank.exception.EntityNotFoundException;
import com.finance.minibank.exception.NoDataFoundException;
import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionType;
import com.finance.minibank.repository.BankTransactionRepository;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.*;
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
public class BankTransactionServiceTest {

    private static final Long VALID_TRANSACTION_ID = 1L;
    private static final Long INVALID_TRANSACTION_ID = 4L;
    private static final Long VALID_ACCOUNT_ID = 1L;
    private static final Long INVALID_ACCOUNT_ID = 3L;
    private static final Double VALID_AMOUNT = 120.0;
    private static final Double INVALID_AMOUNT = -150.0;

    @InjectMocks
    private BankTransactionServiceImpl bankTransactionService;

    @Mock
    private BankTransactionRepository bankTransactionRepository;

    @Before
    public void init(){

        BankTransaction bankTransaction = new BankTransaction(VALID_TRANSACTION_ID,VALID_AMOUNT,VALID_ACCOUNT_ID, BankTransactionType.DEPOSIT);
        BankTransaction bankTransaction1 = new BankTransaction(2L,VALID_AMOUNT,VALID_ACCOUNT_ID, BankTransactionType.WITHDRAW);

        when(bankTransactionRepository.findById(VALID_TRANSACTION_ID)).thenReturn(Optional.of(bankTransaction));
        when(bankTransactionRepository.findById(INVALID_TRANSACTION_ID)).thenReturn(Optional.empty());

        when(bankTransactionRepository.findByAccountId(VALID_ACCOUNT_ID)).thenReturn(Arrays.asList(bankTransaction,bankTransaction1));
        when(bankTransactionRepository.findByAccountId(INVALID_ACCOUNT_ID)).thenReturn(Arrays.asList());


    }

    @Test
    public void getBankTransactionById_oneBankTransactionPresentAndOneIsMissing() {

        BankTransaction bankTransaction = bankTransactionService.getBankTransactionById(VALID_TRANSACTION_ID);
        assertThat(bankTransaction.getAmount()).isEqualTo(VALID_AMOUNT);
        assertThat(bankTransaction.getAccountId()).isEqualTo(VALID_ACCOUNT_ID);
        assertThat(bankTransaction.getTransactionType()).isEqualTo(BankTransactionType.DEPOSIT);

        Exception thrown = assertThrows(EntityNotFoundException.class, () -> {
            bankTransactionService.getBankTransactionById(INVALID_TRANSACTION_ID);
        });
        Assert.assertTrue(StringUtils.containsIgnoreCase(thrown.getMessage(), "not found"));
    }


    @Test
    public void getBankTransactionsByAccountId_twoBankTransactionsArePresent_AnotherAccountHasNoTransactions(){

        List<BankTransaction> transactionList = bankTransactionService.getBankTransactionByAccountId(VALID_ACCOUNT_ID);
        Assertions.assertThat(transactionList).hasSize(2);

        //account with no transactions
        Exception thrown = assertThrows(NoDataFoundException.class, () -> {
            bankTransactionService.getBankTransactionByAccountId(INVALID_ACCOUNT_ID);
        });
        Assert.assertTrue(StringUtils.containsIgnoreCase(thrown.getMessage(), "no transactions were found"));

    }
}
