package com.finance.minibank.repository;

import com.finance.minibank.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
}
