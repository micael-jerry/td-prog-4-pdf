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

    default List<Employee> findAllByFunctionLastnameFirstnameSexAllContainsIgnoreCaseWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String orderBy,
            String direction
    ) {
        if (direction.equals("ASC")) {
            return findAllByFunctionLastnameFirstnameSexAllContainsIgnoreCaseWithSortAsc(function, lastname, firstname, sex, orderBy);
        }
        return findAllByFunctionLastnameFirstnameSexAllContainsIgnoreCaseWithSortDesc(function, lastname, firstname, sex, orderBy);
    }

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "ASC",
            nativeQuery = true
    )
    List<Employee> findAllByFunctionLastnameFirstnameSexAllContainsIgnoreCaseWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String orderBy
    );

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "DESC",
            nativeQuery = true
    )
    List<Employee> findAllByFunctionLastnameFirstnameSexAllContainsIgnoreCaseWithSortDesc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String orderBy
    );
}
