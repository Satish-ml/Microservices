package com.easybytes.accounts.Service.Impl;

import com.easybytes.accounts.Constants.AccountsConstants;
import com.easybytes.accounts.DTO.AccountsDTO;
import com.easybytes.accounts.DTO.CustomerDTO;
import com.easybytes.accounts.Exception.CustomerAlreadyExistException;
import com.easybytes.accounts.Exception.ResourceNotFoundException;
import com.easybytes.accounts.Mapper.AccountsMapper;
import com.easybytes.accounts.Mapper.CustomerMapper;
import com.easybytes.accounts.Repository.AccountsRepository;
import com.easybytes.accounts.Repository.CustomerRepository;
import com.easybytes.accounts.Service.IAccountService;
import com.easybytes.accounts.entity.Accounts;
import com.easybytes.accounts.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Primary
@Service @AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
    Customer customer= CustomerMapper.mapToCustomer(customerDTO,new Customer());
    Optional<Customer> customer1=customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
    if(customer1.isPresent()){
        throw new CustomerAlreadyExistException("Duplicate customer with mobile number "+customerDTO.getMobileNumber());
    }

    Customer savedCustomer = customerRepository.save(customer);

    accountsRepository.save(createNewAccount(savedCustomer));

    }


    private Accounts createNewAccount(Customer customer){
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomNumber = 1000000000L + new Random().nextInt(900000000);


        accounts.setAccountNumber(randomNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);

        return accounts;
    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {
       Customer customer = customerRepository.findBymobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

       Accounts account = accountsRepository.findBycustomerId(customer.getCustomerId()).orElseThrow(
               ()->new ResourceNotFoundException("Account","Customer ID",customer.getCustomerId().toString())
       );
       CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDto(account,new AccountsDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccountDetails(CustomerDTO customerDTO) {
        boolean isUpdated=false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();

        if(accountsDTO!=null){
            Accounts accounts = accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account", "AccountNumber", accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDTO,accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;

    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
