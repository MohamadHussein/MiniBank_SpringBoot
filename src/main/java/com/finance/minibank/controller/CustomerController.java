package com.finance.minibank.controller;

import com.finance.minibank.model.Customer;
import com.finance.minibank.model.CustomerDTO;
import com.finance.minibank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers() {
        try {
            return new ResponseEntity<>(
                    customerService.getAllCustomers(),
                    HttpStatus.OK);

        } catch (Exception e) {
            return errResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            return new ResponseEntity<>(
        customerService.saveNewCustomer(customerDTO),HttpStatus.CREATED);
        } catch (Exception e){
            return errResponse(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        try {
            Optional<Customer> optCust = customerService.getCustomerById(id);
            if (optCust.isPresent()) {
                return new ResponseEntity<>(
                        optCust.get(),
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
