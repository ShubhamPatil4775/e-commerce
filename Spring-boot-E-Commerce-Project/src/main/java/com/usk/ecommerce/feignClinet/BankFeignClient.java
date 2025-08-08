package com.usk.ecommerce.feignClinet;

import com.usk.ecommerce.dto.BankDebitCreditResponse;
import com.usk.ecommerce.dto.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "http://BANK-SERVICE/bank/api/bank")
public interface BankFeignClient {

    @PostMapping("/debit")
    BankDebitCreditResponse debitAccount(@RequestBody TransactionRequest request);
}
