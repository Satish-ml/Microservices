package com.easybytes.accounts.Repository;

import com.easybytes.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findBycustomerId(Long Id);
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
