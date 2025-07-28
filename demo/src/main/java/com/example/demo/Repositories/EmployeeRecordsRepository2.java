package com.example.demo.Repositories;

import com.example.demo.Entity.EmployeeRecords2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRecordsRepository2 extends JpaRepository<EmployeeRecords2,Integer> {
}
