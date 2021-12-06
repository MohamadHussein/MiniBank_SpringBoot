package com.finance.minibank.service;


import com.finance.minibank.model.AccountDTO;
import com.finance.minibank.model.Customer;
import com.finance.minibank.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long id);

    Customer saveNewCustomer(CustomerDTO customerDTO);

    Customer updateCustomer(Customer oldCustomer, CustomerDTO newCustomer);

    void deleteCustomer(Customer customer);

    Customer addNewAccountToCustomer(Long customerId, AccountDTO accountDTO);
}