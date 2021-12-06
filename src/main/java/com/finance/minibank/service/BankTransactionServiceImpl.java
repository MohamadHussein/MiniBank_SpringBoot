package com.finance.minibank.service;

import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionDTO;
import com.finance.minibank.repository.AccountRepository;
import com.finance.minibank.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BankTransactionServiceImpl(BankTransactionRepository bankTransactionRepository, AccountRepository accountRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<BankTransaction> getAllBankTransaction() {
        List<BankTransaction> transactionList = new ArrayList<>();
        bankTransactionRepository.findAll().forEach(transactionList::add);
        return transactionList;
    }

    @Override
    public List<BankTransaction> getBankTransactionByAccountId(Long accountId) {
        List<BankTransaction> bankTransactionList = new ArrayList<>();
        bankTransactionRepository.findByAccountId(accountId).forEach(bankTransactionList::add);
        return bankTransactionList;
    }

    @Override
    public Optional<BankTransaction> getBankTransactionById(Long id) {
        return bankTransactionRepository.findById(id);
    }

    @Override
    public BankTransaction saveNewBankTransaction(BankTransactionDTO bankTransactionDTO) {
        BankTransaction bankTransaction = DTOtoBankTransaction(bankTransactionDTO);
        return bankTransactionRepository.save(bankTransaction);
    }

    @Override
    public BankTransaction updateBankTransaction(BankTransaction oldBankTransaction, BankTransactionDTO bankTransactionDTO) {
        oldBankTransaction.setTransactionType(bankTransactionDTO.getTransactionType());
        oldBankTransaction.setAmount(bankTransactionDTO.getAmount());
        return bankTransactionRepository.save(oldBankTransaction);
    }

    private BankTransaction DTOtoBankTransaction(BankTransactionDTO bankTransactionDTO) {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setAmount(bankTransactionDTO.getAmount());
        bankTransaction.setTransactionType(bankTransactionDTO.getTransactionType());
        if (bankTransactionDTO.getAccountId() != null) {
            bankTransaction.setAccount(accountRepository.findById(bankTransactionDTO.getAccountId()).get());
        }
        return bankTransaction;
    }
}
