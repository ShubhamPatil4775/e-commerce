package com.usk.ecommerce.service;

import com.usk.ecommerce.dto.BankDebitCreditResponse;

public interface BankAccountService {
    BankDebitCreditResponse debit(String accountNo,double amount);
}
