package com.spring.boot.repository;

import com.spring.boot.model.Employee;
import com.spring.boot.model.Sex;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getByCnapsNumber(String cnapsNumber);
    List<Employee> findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCase(String function, String lastName, String firstName, Sort sort);

    List<Employee> findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCaseAndSex(String function, String lastname, String firstname, Sex sex, Sort sort);
}
