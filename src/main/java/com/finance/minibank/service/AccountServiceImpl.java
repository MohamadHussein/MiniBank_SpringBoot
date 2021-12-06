package com.finance.minibank.service;

import com.finance.minibank.model.*;
import com.finance.minibank.repository.AccountRepository;
import com.finance.minibank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }
    @Transactional
    @Override
    public Account saveNewAccount(AccountDTO accountDTO) {
        Account account = DTOtoAccount(accountDTO);
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account oldAccount, AccountDTO newAccount) {
        oldAccount.setBalance(newAccount.getBalance());
        return accountRepository.save(oldAccount);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.delete(account);

    }

    @Override
    public Account addNewTransactionToAccount(Long accountID, BankTransactionDTO bankTransactionDTO) {
        Account account = accountRepository.findById(accountID).get();
        account.addTransactionToAccount(DTOtoBankTransaction(bankTransactionDTO));
        return accountRepository.save(account);
    }

    private Account DTOtoAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setCustomer(customerRepository.findById(accountDTO.getCustomerID()).get());
        return account;
    }
    private BankTransaction DTOtoBankTransaction(BankTransactionDTO bankTransactionDTO){
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setAmount(bankTransactionDTO.getAmount());
        return bankTransaction;
    }
}
