package com.finance.minibank.controller;

import com.finance.minibank.exception.EntityNotFoundException;
import com.finance.minibank.model.Customer;
import com.finance.minibank.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;

    @Test
    public void findAllCustomers_ReturnedCorrectNumberOfCustomers_Status200() throws Exception{
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(
                new Customer(1L, "loyal customer","customer"),
                new Customer(2L, "happy customer","client")

        ));
        mockMvc.perform(get("/customers/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"));
    }

    @Test
    public void findByCustomerId_CustomerNotFound_Status404() throws Exception {
        when(customerService.getCustomerById(2L)).thenThrow(new EntityNotFoundException(String.format("customer with id %d not found",2L)));
        mockMvc.perform(get("/customers/{id}", 2L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",containsStringIgnoringCase("not found")));

        verify(customerService, times(1)).getCustomerById(2L);
    }


}
