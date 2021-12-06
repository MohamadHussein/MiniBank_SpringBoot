package com.finance.minibank.controller;

import com.finance.minibank.model.Account;
import com.finance.minibank.model.AccountDTO;
import com.finance.minibank.model.Customer;
import com.finance.minibank.model.CustomerDTO;
import com.finance.minibank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        try {
            return new ResponseEntity<>(
                    accountService.getAllAccounts(),
                    HttpStatus.OK);

        } catch (Exception e) {
            return errResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO){
        try {
            return new ResponseEntity<>(
                    accountService.saveNewAccount(accountDTO),HttpStatus.CREATED);
        } catch (Exception e){
            return errResponse(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id){
        try {
            Optional<Account> optionalAccount = accountService.getAccountById(id);
            if (optionalAccount.isPresent()) {
                return new ResponseEntity<>(
                        optionalAccount.get(),
                        HttpStatus.OK);
            } else {
                return errResponse("customer not found ", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return errResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<?> errResponse(String msg, HttpStatus status) {
        return new ResponseEntity<>("Error Happened: " + msg, status);
    }
}
