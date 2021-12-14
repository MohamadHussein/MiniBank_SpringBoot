package com.finance.minibank.service;


import com.finance.minibank.model.Customer;
import com.finance.minibank.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer saveNewCustomer(CustomerDTO customerDTO);



}