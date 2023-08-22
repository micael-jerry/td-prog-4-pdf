package com.spring.boot.cnaps.repository;

import com.spring.boot.cnaps.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnapsEmployeeJpaRepository extends JpaRepository<Employee, Integer> {
    Employee getByCnapsNumber(String cnapsNumber);
}
