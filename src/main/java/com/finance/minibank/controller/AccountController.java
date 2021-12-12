package com.finance.minibank.controller;

import com.finance.minibank.model.AccountDTO;
import com.finance.minibank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllAccounts() {
        return new ResponseEntity<>(
                accountService.getAllAccounts(),
                HttpStatus.OK);
    }


    @PostMapping(value = "/")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.saveNewAccount(accountDTO),
                HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        return new ResponseEntity<>(
                accountService.getAccountById(id),
                HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getByCustomerId(@PathVariable Long customerId) {
        return new ResponseEntity<>(accountService.getAccountsByCustomerId(customerId),
                HttpStatus.OK);
    }

}
