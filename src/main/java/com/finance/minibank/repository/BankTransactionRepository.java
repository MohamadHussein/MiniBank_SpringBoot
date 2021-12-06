package com.finance.minibank.repository;

import com.finance.minibank.model.BankTransaction;
import org.springframework.data.repository.CrudRepository;

public interface BankTransactionRepository extends CrudRepository<BankTransaction,Long> {
}
