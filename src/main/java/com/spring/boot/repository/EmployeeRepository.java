package com.spring.boot.repository;

import com.spring.boot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getByCnapsNumber(String cnapsNumber);

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%')",
            nativeQuery = true
    )
    List<Employee> findAllByFunctionLastnameFirstnameSexAllContainsIgnoreCase(
            String function,
            String lastname,
            String firstname,
            String sex
    );

    @Query(
            value = "SELECT * FROM employee",
            nativeQuery = true
    )
    List<Employee> findAllByFunctionLastnameFirstnameSexAllContainsIgnoreCaseWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String orderBy,
            String direction
    );
}
