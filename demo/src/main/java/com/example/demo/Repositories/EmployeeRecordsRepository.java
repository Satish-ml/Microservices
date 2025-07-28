package com.example.demo.Repositories;

import com.example.demo.Entity.EmployeeRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRecordsRepository extends JpaRepository<EmployeeRecords,String> {


}
