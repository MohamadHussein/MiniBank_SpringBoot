package com.finance.minibank.service;

import com.finance.minibank.model.Account;
import com.finance.minibank.model.AccountDTO;
import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionDTO;

import java.util.List;
import java.util.Optional;

public interface BankTransactionService {
    List<BankTransaction> getAllBankTransaction();

    List<BankTransaction> getBankTransactionByAccountId(Long accountId);

    Optional<BankTransaction> getBankTransactionById(Long id);

    BankTransaction saveNewBankTransaction(BankTransactionDTO bankTransactionDTO);

    BankTransaction updateBankTransaction(BankTransaction oldBankTransaction, BankTransactionDTO bankTransactionDTO);


}
