package com.finance.minibank.repository;

import com.finance.minibank.model.BankTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankTransactionRepository extends CrudRepository<BankTransaction,Long> {
    List<BankTransaction> findByAccountId(Long accountId);
}
