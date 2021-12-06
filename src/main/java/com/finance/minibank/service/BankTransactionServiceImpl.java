package com.finance.minibank.service;

import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionDTO;

import java.util.List;
import java.util.Optional;

public class BankTransactionServiceImpl implements BankTransactionService {

    @Override
    public List<BankTransaction> getAllBankTransaction() {
        return null;
    }

    @Override
    public Optional<BankTransaction> getBankTransactionById(Long id) {
        return Optional.empty();
    }

    @Override
    public BankTransaction saveNewBankTransaction(BankTransactionDTO bankTransactionDTO) {
        return null;
    }

    @Override
    public BankTransaction updateBankTransaction(BankTransaction oldBankTransaction, BankTransactionDTO bankTransactionDTO) {
        return null;
    }
}
