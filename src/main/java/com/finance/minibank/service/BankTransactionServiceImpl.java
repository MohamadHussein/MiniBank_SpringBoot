package com.finance.minibank.service;

import com.finance.minibank.exception.EntityNotFoundException;
import com.finance.minibank.exception.NoDataFoundException;
import com.finance.minibank.model.Account;
import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionDTO;
import com.finance.minibank.repository.BankTransactionRepository;
import com.finance.minibank.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;


    @Autowired
    public BankTransactionServiceImpl(BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
    }


    @Override
    public List<BankTransaction> getAllBankTransaction() {
        List<BankTransaction> bankTransactionList = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactionRepository.findAll()) {
            bankTransactionList.add(bankTransaction);
        }
        if (bankTransactionList.size()==0){
            throw new NoDataFoundException("no transactions were found");
        } else {
            return bankTransactionList;
        }
    }


    @Override
    public List<BankTransaction> getBankTransactionByAccountId(Long accountId) {
        List<BankTransaction> bankTransactionList = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactionRepository.findByAccountId(accountId)) {
            bankTransactionList.add(bankTransaction);
        }
        if (bankTransactionList.size()==0){
            throw new NoDataFoundException(String.format("no transactions were found for account id:: %d",accountId));
        } else {
            return bankTransactionList;
        }
    }


    @Override
    public BankTransaction getBankTransactionById(Long id) {
        Optional<BankTransaction> transactionOptional = bankTransactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            return transactionOptional.get();
        } else {
            throw new EntityNotFoundException(String.format("bank transaction with id %d not found",id));
        }

    }

    @Transactional
    @Override
    public BankTransaction saveNewBankTransaction(BankTransactionDTO bankTransactionDTO) {
        BankTransaction bankTransaction = DtoUtils.DTOtoBankTransaction(bankTransactionDTO);
        bankTransaction.getAccount().addTransactionToAccount(bankTransaction);
        return bankTransactionRepository.save(bankTransaction);
    }



}
