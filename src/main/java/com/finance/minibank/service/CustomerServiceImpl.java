package com.finance.minibank.service;

import com.finance.minibank.exception.EntityNotFoundException;
import com.finance.minibank.exception.NoDataFoundException;
import com.finance.minibank.model.Customer;
import com.finance.minibank.model.CustomerDTO;
import com.finance.minibank.repository.CustomerRepository;
import com.finance.minibank.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers()  {

        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            customerList.add(customer);
        }
        if (customerList.size()==0){
            throw new NoDataFoundException("no customers were found ");
        } else {
            return customerList;
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
                throw new EntityNotFoundException(String.format("customer with id %d not found",id));
        }
    }

    @Override
    public Customer saveNewCustomer(CustomerDTO customerDTO) {
        Customer customer = DtoUtils.DTOtoCustomer(customerDTO);
        customerRepository.save(customer);
        System.out.println(customer.getId());
        return customerRepository.save(customer);
    }

}
