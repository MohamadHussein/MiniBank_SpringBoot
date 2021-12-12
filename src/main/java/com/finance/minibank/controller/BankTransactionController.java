package com.finance.minibank.controller;

import com.finance.minibank.model.BankTransactionDTO;
import com.finance.minibank.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class BankTransactionController {
    private final BankTransactionService bankTransactionService;

    @Autowired
    public BankTransactionController(BankTransactionService bankTransactionService) {
        this.bankTransactionService = bankTransactionService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllBankTransaction() {
        return new ResponseEntity<>(bankTransactionService.getAllBankTransaction(),
                HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<?> createBankTransaction(@RequestBody @Valid BankTransactionDTO bankTransactionDTO) {
        return new ResponseEntity<>(
                bankTransactionService.saveNewBankTransaction(bankTransactionDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBankTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(
                bankTransactionService.getBankTransactionById(id),
                HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> getByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(bankTransactionService.getBankTransactionByAccountId(accountId), HttpStatus.OK);
    }

}
