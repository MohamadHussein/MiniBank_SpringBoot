package com.finance.minibank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.minibank.model.Account;
import com.finance.minibank.model.AccountDTO;
import com.finance.minibank.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Long VALID_ID = 1L;
    private static final Double VALID_BALANCE = 120.0;
    private static final Double INVALID_BALANCE = -120.0;

    @Test
    public void createAccount_negativeBalanceValidation_Status400() throws Exception{
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(VALID_ID);
        accountDTO.setBalance(INVALID_BALANCE); //initial balance is not allowed to be less than zero
        mockMvc.perform(post("/accounts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsStringIgnoringCase("can't be negative")));
        verifyNoInteractions(accountService); //to verify that accountService was never invoked
    }

    @Test
    public void createAccount_blankCustomerIdValidation_Status400() throws Exception{
        AccountDTO accountDTO = new AccountDTO();
        //notice that we didn't set the customerId
        accountDTO.setBalance(VALID_BALANCE);
        mockMvc.perform(post("/accounts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsStringIgnoringCase("missing field: customer id")));
        verifyNoInteractions(accountService); //to verify that accountService was never invoked
    }

    @Test
    public void createAccount_successfullyCreated_Status200() throws Exception{
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(VALID_ID);
        accountDTO.setBalance(VALID_BALANCE); //initial balance is not allowed to be less than zero
        Account accountToReturn =  new Account(VALID_ID, accountDTO.getCustomerID(), accountDTO.getBalance());
        when(accountService.saveNewAccount(isA(AccountDTO.class))).thenReturn(accountToReturn);
        mockMvc.perform(post("/accounts/")
                        .content(objectMapper.writeValueAsString(accountDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.customerId").value(VALID_ID))
                .andExpect(jsonPath("$.balance").value(VALID_BALANCE));

        verify(accountService, times(1)).saveNewAccount(isA(AccountDTO.class)); //verify single invocation
        verifyNoMoreInteractions(accountService); //verify no unnecessary invocations
    }
}
