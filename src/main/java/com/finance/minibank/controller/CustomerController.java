package com.finance.minibank.controller;

import com.finance.minibank.model.Customer;
import com.finance.minibank.model.CustomerDTO;
import com.finance.minibank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
        return new ResponseEntity<>(
                customerService.getAllCustomers(),
                HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return new ResponseEntity<>(
                customerService.saveNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer( @PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer,
                HttpStatus.OK);
    }

}
