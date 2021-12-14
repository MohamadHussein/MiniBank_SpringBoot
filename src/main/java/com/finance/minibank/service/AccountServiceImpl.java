package com.finance.minibank.service;

import com.finance.minibank.exception.EntityNotFoundException;
import com.finance.minibank.exception.NoDataFoundException;
import com.finance.minibank.model.*;
import com.finance.minibank.repository.AccountRepository;
import com.finance.minibank.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;


    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }


    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        for (Account account : accountRepository.findAll()) {
            accountList.add(account);
        }
        if (accountList.size()==0){
            throw new NoDataFoundException("no accounts were found ");
        } else {
            return accountList;
        }
    }


    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        List<Account> accountList= accountRepository.findByCustomerId(customerId);
        if (accountList.size()==0){
            throw new NoDataFoundException(String.format("no accounts were found for customer with id :: %d",customerId));
        } else {
            return accountList;
        }
    }


    @Override
    public Account getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new EntityNotFoundException(String.format("account with id %d not found",id));
        }
    }

    @Transactional
    @Override
    public Account saveNewAccount(AccountDTO accountDTO) {

        Account account = DtoUtils.DTOtoAccount(accountDTO);
        if (accountDTO.getBalance() > 0.0) {
            //make a transaction
            BankTransactionDTO bankTransactionDTO = new BankTransactionDTO();
            bankTransactionDTO.setTransactionType(BankTransactionType.DEPOSIT);
            bankTransactionDTO.setAmount(accountDTO.getBalance());
            BankTransaction bankTransaction = DtoUtils.DTOtoBankTransaction(bankTransactionDTO);
            account.addTransactionToAccount(bankTransaction);
            bankTransaction.setAccount(account);
        }
        return accountRepository.save(account);
    }


}
