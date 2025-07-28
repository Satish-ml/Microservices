package com.easybytes.accounts.Service.Impl;

import com.easybytes.accounts.DTO.CustomerDTO;
import com.easybytes.accounts.Service.IAccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("service1")
public class AccountServiceImpl2 implements IAccountService {
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        System.out.println("Customer Name "+customerDTO.getName());
    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateAccountDetails(CustomerDTO customerDTO) {
        return false;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        return false;
    }
}
