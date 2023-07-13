package com.spring.boot.repository;

import com.spring.boot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByPersonnelNumberContainsIgnoreCaseOrLastnameContainsIgnoreCaseOrFirstnameContainsIgnoreCase(String personnelNumber,String lastName, String firstName);
}
