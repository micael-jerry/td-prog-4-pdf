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
    List<Employee> findAllByCriteria(
            String function,
            String lastname,
            String firstname,
            String sex
    );

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "AND start_date >= TO_DATE(:startDate, 'YYYY-MM-DD') " +
                    "AND departure_date <= TO_DATE(:departureDate, 'YYYY-MM-DD')",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaBetweenStartAndDeparture(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate
    );

    default List<Employee> findAllByCriteriaWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String orderBy,
            String direction
    ) {
        if (direction.equals("ASC")) {
            return findAllByCriteriaWithSortAsc(function, lastname, firstname, sex, orderBy);
        }
        return findAllByCriteriaWithSortDesc(function, lastname, firstname, sex, orderBy);
    }

    default List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate,
            String orderBy,
            String direction
    ) {
        if (direction.equals("ASC")) {
            return findAllByCriteriaBetweenStartAndDepartureWithSortAsc(function, lastname, firstname, sex, startDate, departureDate, orderBy);
        }
        return findAllByCriteriaBetweenStartAndDepartureWithSortDesc(function, lastname, firstname, sex, startDate, departureDate, orderBy);
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
    List<Employee> findAllByCriteriaWithSortAsc(
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
                    "AND start_date >= TO_DATE(:startDate, 'YYYY-MM-DD') " +
                    "AND departure_date <= TO_DATE(:departureDate, 'YYYY-MM-DD') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "ASC",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate,
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
    List<Employee> findAllByCriteriaWithSortDesc(
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
                    "AND start_date >= TO_DATE(:startDate, 'YYYY-MM-DD') " +
                    "AND departure_date <= TO_DATE(:departureDate, 'YYYY-MM-DD') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "DESC",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSortDesc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate,
            String orderBy
    );
}
