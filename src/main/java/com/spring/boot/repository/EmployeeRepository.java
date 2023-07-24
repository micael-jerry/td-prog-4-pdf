package com.spring.boot.repository;

import com.spring.boot.model.Employee;
import com.spring.boot.model.Sex;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getByCnapsNumber(String cnapsNumber);

//    @Query("SELECT e FROM Employee e WHERE LOWER(e.function) LIKE %:function% AND LOWER(e.lastname) LIKE %:lastname% AND LOWER(e.firstname) LIKE %:firstname%")
//    List<Employee> findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCase(
//            String function,
//            String lastname,
//            String firstname,
//            Sort sort
//    );

    List<Employee> findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCase(String function, String lastname, String firstname, Sort sort);

//    @Query("SELECT e FROM Employee e WHERE LOWER(e.function) LIKE %:function% AND LOWER(e.lastname) LIKE %:lastname% AND LOWER(e.firstname) LIKE %:firstname% AND e.sex = :sex")
//    List<Employee> findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCaseAndSex(
//            String function,
//            String lastname,
//            String firstname,
//            Sex sex,
//            Sort sort
//    );

    List<Employee> findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCaseAndSex(String function, String lastname, String firstname, Sex sex, Sort sort);
}
