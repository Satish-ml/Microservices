package com.easybytes.accounts.Repository;


import com.easybytes.accounts.entity.Accounts;
import com.easybytes.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);
    Optional<Customer> findBymobileNumber(String number);

}
