package com.finance.minibank.controller;

import com.finance.minibank.model.Account;
import com.finance.minibank.model.AccountDTO;
import com.finance.minibank.model.BankTransaction;
import com.finance.minibank.model.BankTransactionDTO;
import com.finance.minibank.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class BankTransactionController {
    private final BankTransactionService bankTransactionService;

    @Autowired
    public BankTransactionController(BankTransactionService bankTransactionService) {
        this.bankTransactionService = bankTransactionService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllBankTransaction(){
        try {
             return new ResponseEntity<>(bankTransactionService.getAllBankTransaction(),HttpStatus.OK) ;
        }catch (Exception e){
            return errResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createBankTransaction(@RequestBody BankTransactionDTO bankTransactionDTO){
        try {
            return new ResponseEntity<>(
                    bankTransactionService.saveNewBankTransaction(bankTransactionDTO), HttpStatus.CREATED);
        } catch (Exception e){
            return errResponse(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBankTransaction(@PathVariable Long id){
        try {
            Optional<BankTransaction> optionalBankTransaction = bankTransactionService.getBankTransactionById(id);
            if (optionalBankTransaction.isPresent()) {
                return new ResponseEntity<>(
                        optionalBankTransaction.get(),
                        HttpStatus.OK);
            } else {
                return errResponse("Bank Transaction not found ", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return errResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> getByAccountId(@PathVariable Long accountId){
        try {
            return new ResponseEntity<>(bankTransactionService.getBankTransactionByAccountId(accountId), HttpStatus.OK);
        }catch (Exception e){
            return errResponse(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> errResponse(String msg, HttpStatus status) {
        return new ResponseEntity<>("Error Happened: " + msg, status);
    }
}
