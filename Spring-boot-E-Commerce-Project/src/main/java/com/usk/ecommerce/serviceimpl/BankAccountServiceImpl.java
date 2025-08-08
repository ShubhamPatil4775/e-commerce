package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.BankDebitCreditResponse;
import com.usk.ecommerce.dto.TransactionRequest;
import com.usk.ecommerce.feignClinet.BankFeignClient;
import com.usk.ecommerce.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankFeignClient bankFeignClient;

    @Override
    public BankDebitCreditResponse debit(String accountNo, double amount) {
        TransactionRequest request = new TransactionRequest(accountNo,amount);
        try{
            return bankFeignClient.debitAccount(request);
        }catch (Exception e){
            e.printStackTrace();
            String error= "Error while communicating with bank or transaction failed";
            return new BankDebitCreditResponse(false,error,accountNo,0.0,null);
        }
    }
}
