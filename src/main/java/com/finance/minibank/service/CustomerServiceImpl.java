package com.finance.minibank.service;

import com.finance.minibank.model.Account;
import com.finance.minibank.model.AccountDTO;
import com.finance.minibank.model.Customer;
import com.finance.minibank.model.CustomerDTO;
import com.finance.minibank.repository.CustomerRepository;
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
        customerRepository.findAll().forEach(customerList::add);
        System.out.println(customerList.size());
        return customerList;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer saveNewCustomer(CustomerDTO customerDTO) {
        Customer customer = DTOtoCustomer(customerDTO);
        customerRepository.save(customer);
        System.out.println(customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer oldCustomer, CustomerDTO newCustomer) {
        oldCustomer.setName(newCustomer.getName());
        oldCustomer.setSurname(newCustomer.getSurname());
        return customerRepository.save(oldCustomer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer addNewAccountToCustomer(Long customerId, AccountDTO accountDTO) {
        Customer customer = customerRepository.findById(customerId).get();

        customer.addToAccountList(DTOtoAccount(accountDTO));
        return customerRepository.save(customer);
    }

    private Customer DTOtoCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        return customer;
    }

    private Account DTOtoAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setCustomer(customerRepository.findById(accountDTO.getCustomerID()).get());
        return account;
    }
}
