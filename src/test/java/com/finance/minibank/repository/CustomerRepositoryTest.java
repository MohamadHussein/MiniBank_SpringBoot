package com.finance.minibank.repository;

import com.finance.minibank.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;
    //first customer info
    private static final Long CUSTOMER_VALID_ID = 1L;
    private static final String CUSTOMER_NAME = "jack sparrow";
    private static final String CUSTOMER_SURNAME = "sparrow";

    @Before
    public void init(){
        Customer customer1 = new Customer(CUSTOMER_NAME,CUSTOMER_SURNAME);
        Customer customer2 = new Customer("jack black","black");
        Customer customer3 = new Customer("willy wonka","wonka");

        testEntityManager.persistAndFlush(customer1);
        testEntityManager.persistAndFlush(customer2);
        testEntityManager.persistAndFlush(customer3);
    }

    @Test
    public void findAllCustomers_CustomersArePresent(){
        Iterable<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(3);
    }


    @Test
    public void findByCustomerId_CustomerIsPresent(){
        Optional<Customer> customerOptional = customerRepository.findById(CUSTOMER_VALID_ID);
        assertTrue(customerOptional.isPresent());
        Customer foundCustomer = customerOptional.get();
        assertThat(foundCustomer.getName()).isEqualTo(CUSTOMER_NAME);
        assertThat(foundCustomer.getSurname()).isEqualTo(CUSTOMER_SURNAME);
    }





}
