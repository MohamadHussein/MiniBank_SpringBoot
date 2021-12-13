package com.finance.minibank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.minibank.model.*;
import com.finance.minibank.service.AccountService;
import com.finance.minibank.service.BankTransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(BankTransactionController.class)
public class BankTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankTransactionService bankTransactionService;

    @Autowired
    private ObjectMapper objectMapper;





    private static final Long VALID_ACCOUNT_ID = 1L;
    private static final Double VALID_AMOUNT = 120.0;
    private static final Double INVALID_AMOUNT = -150.0;


    @Test
    public void createBankTransaction_SuccessfullyCreated_Status200() throws Exception{
        BankTransactionDTO bankTransactionDTO = new BankTransactionDTO();
        bankTransactionDTO.setAmount(VALID_AMOUNT);
        bankTransactionDTO.setAccountId(VALID_ACCOUNT_ID);
        bankTransactionDTO.setTransactionType(BankTransactionType.DEPOSIT);
        BankTransaction bankTransactionToReturn = new BankTransaction(1L,VALID_AMOUNT,BankTransactionType.DEPOSIT);
        when(bankTransactionService.saveNewBankTransaction(isA(BankTransactionDTO.class))).thenReturn(bankTransactionToReturn);
        mockMvc.perform(post("/transactions/").
                        content(objectMapper.writeValueAsString(bankTransactionDTO)).
                contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isCreated()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.transactionType").value("DEPOSIT"))
                .andExpect(jsonPath("$.amount").value(VALID_AMOUNT));
        verify(bankTransactionService,times(1)).saveNewBankTransaction(isA(BankTransactionDTO.class));
    }
    @Test
    public void createBankTransaction_negativeAmount_Status400() throws Exception {
        BankTransactionDTO bankTransactionDTO = new BankTransactionDTO();
        bankTransactionDTO.setAmount(INVALID_AMOUNT);
        bankTransactionDTO.setAccountId(VALID_ACCOUNT_ID);
        bankTransactionDTO.setTransactionType(BankTransactionType.DEPOSIT);
        BankTransaction bankTransactionToReturn = new BankTransaction(1L, INVALID_AMOUNT, BankTransactionType.DEPOSIT);

        mockMvc.perform(post("/transactions/").
                        content(objectMapper.writeValueAsString(bankTransactionDTO)).
                        contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",containsStringIgnoringCase("at least 1")));
        verifyNoInteractions(bankTransactionService);
    }

    @Test
    public void createBankTransaction_MissingTransactionType_Status400() throws Exception {
        BankTransactionDTO bankTransactionDTO = new BankTransactionDTO();
        bankTransactionDTO.setAmount(VALID_AMOUNT);
        bankTransactionDTO.setAccountId(VALID_ACCOUNT_ID);
//        we are not providing transactionType field which is mandatory
        BankTransaction bankTransactionToReturn = new BankTransaction(1L, VALID_AMOUNT, BankTransactionType.DEPOSIT);

        mockMvc.perform(post("/transactions/").
                        content(objectMapper.writeValueAsString(bankTransactionDTO)).
                        contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isBadRequest()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",containsStringIgnoringCase("missing field")));
        verifyNoInteractions(bankTransactionService);
    }

}
