package com.finance.minibank.service;

import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionDTO;

import java.util.List;

public interface BankTransactionService {
    List<BankTransaction> getAllBankTransaction();

    List<BankTransaction> getBankTransactionByAccountId(Long accountId);

    BankTransaction getBankTransactionById(Long id);

    BankTransaction saveNewBankTransaction(BankTransactionDTO bankTransactionDTO);


}
