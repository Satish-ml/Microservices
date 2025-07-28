package com.easybytes.accounts.Service;

import com.easybytes.accounts.DTO.CustomerDTO;

public interface IAccountService {

    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountDetails(String mobileNumber);

    boolean updateAccountDetails(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);
}

