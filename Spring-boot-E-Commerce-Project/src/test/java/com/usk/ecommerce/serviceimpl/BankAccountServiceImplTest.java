package com.usk.ecommerce.serviceimpl;
import com.usk.ecommerce.dto.BankDebitCreditResponse;

import com.usk.ecommerce.feignClinet.BankFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class BankAccountServiceImplTest {

    @Mock
    private BankFeignClient bankFeignClient;

    @InjectMocks
    private BankAccountServiceImpl bankService;

    private BankDebitCreditResponse successResponse;

    @BeforeEach
    void setUp() {
        successResponse = new BankDebitCreditResponse();
        successResponse.setSuccess(true);
        successResponse.setMessage("Transaction successful");
        successResponse.setAccountNo("1010101010");
        successResponse.setNewBalance(4500.0);
        successResponse.setTransactionId("1023242423");
    }

//    @Test
//    void testDebit_Success() {
//        when(bankFeignClient.debitAccount(any(TransactionRequest.class)))
//                .thenReturn(successResponse);
//
////        BankDebitCreditResponse response = bankService.debit("1010101010", 500.0);
//
////        assertTrue(response.isSuccess());
////        assertEquals("Transaction successful", response.getMessage());
////        assertEquals("1010101010", response.getAccountNo());
////        assertEquals(4500.0, response.getNewBalance());
////        assertEquals("1023242423", response.getTransactionId());
//    }

}

