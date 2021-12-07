package com.finance.minibank.service;

import com.finance.minibank.model.*;
import com.finance.minibank.repository.AccountRepository;
import com.finance.minibank.repository.BankTransactionRepository;
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
    private final BankTransactionService bankTransactionService;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, BankTransactionService bankTransactionService) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.bankTransactionService = bankTransactionService;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {

        return accountRepository.findByCustomerId(customerId);

    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account saveNewAccount(AccountDTO accountDTO) {

        Account account = DTOtoAccount(accountDTO);
       try {
           if (accountDTO.getBalance() > 0.0) {
               //make a transaction
               BankTransactionDTO bankTransactionDTO = new BankTransactionDTO();
               bankTransactionDTO.setTransactionType(BankTransactionType.DEPOSIT);
               bankTransactionDTO.setAmount(accountDTO.getBalance());
               BankTransaction bankTransaction = bankTransactionService.saveNewBankTransaction(bankTransactionDTO);
               account.addTransactionToAccount(bankTransaction);
               bankTransaction.setAccount(account);
           }
       } catch (Exception e){
           System.out.println(e.getMessage());
       }

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
    public Account addNewTransactionToAccount(Long accountId, BankTransactionDTO bankTransactionDTO) {
        Account account = accountRepository.findById(accountId).get();
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
