package com.finance.minibank.service;

import com.finance.minibank.model.*;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();

    List<Account> getAccountsByCustomerId(Long customerId);

    Optional<Account> getAccountById(Long id);

    Account saveNewAccount(AccountDTO accountDTO);

    Account updateAccount(Account oldAccount, AccountDTO newAccount);

    void deleteAccount(Account account);

    Account addNewTransactionToAccount(Long accountID, BankTransactionDTO bankTransactionDTO);
}
