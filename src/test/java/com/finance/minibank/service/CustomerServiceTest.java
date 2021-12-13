package com.finance.minibank.service;


import com.finance.minibank.exception.EntityNotFoundException;
import com.finance.minibank.model.Customer;
import com.finance.minibank.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = 2L;
    private static final String CUSTOMER_NAME = "jack sparrow";
    private static final String CUSTOMER_SURNAME ="sparrow";

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void init(){
        Customer customer = new Customer(VALID_ID,CUSTOMER_NAME,CUSTOMER_SURNAME);
        Customer customer1 = new Customer(3L,"Niki Lauda","Lauda");

        //find by ID repo behavior
        when(customerRepository.findById(VALID_ID)).thenReturn(Optional.of(customer));
        when(customerRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
        //find all behavior
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer,customer1));

    }
    @Test
    public void getAllCustomer_twoCustomersArePresent() throws Exception{
        List<Customer> customerList = customerService.getAllCustomers();
        assertThat(customerList).hasSize(2);
    }

    @Test
    public void getCustomerByID_onePresentAndOneIsMissing() throws Exception{
        //existing customer
        Customer customer = customerService.getCustomerById(1L);
        assertThat(customer.getSurname()).isEqualTo(CUSTOMER_SURNAME);
        assertThat(customer.getName()).isEqualTo(CUSTOMER_NAME);

        //non-existent customer
        Exception thrown = assertThrows(EntityNotFoundException.class, () -> {
            customerService.getCustomerById(2L);
        });
        Assert.assertTrue(StringUtils.containsIgnoreCase(thrown.getMessage().toString(), "customer with id 2 not found"));
    }

}
