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
            value = "SELECT DISTINCT e.* FROM employee e " +
                    "LEFT JOIN phone p ON e.id = p.employee_id " +
                    "WHERE  p.country_code LIKE CONCAT('%', :countryCode, '%')",
            nativeQuery = true
    )
    List<Employee> findAllByCountryCode(String countryCode);

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

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "AND start_date >= TO_DATE(:startDate, 'YYYY-MM-DD')",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaAfterStart(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate
    );

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "AND departure_date <= TO_DATE(:departureDate, 'YYYY-MM-DD')",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaBeforeDeparture(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate
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

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "AND start_date >= TO_DATE(:startDate, 'YYYY-MM-DD') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "ASC",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaAfterStartWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String orderBy
    );

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "AND departure_date <= TO_DATE(:departureDate, 'YYYY-MM-DD') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "ASC",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaBeforeDepartureWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate,
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
                    "AND start_date >= TO_DATE(:startDate, 'YYYY-MM-DD') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "DESC",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaAfterStartWithSortDesc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String orderBy
    );

    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE UPPER(function) LIKE CONCAT('%', UPPER(:function), '%') " +
                    "AND UPPER(lastname) LIKE CONCAT('%', UPPER(:lastname), '%') " +
                    "AND UPPER(firstname) LIKE CONCAT('%', UPPER(:firstname), '%') " +
                    "AND UPPER(sex) LIKE CONCAT('%', UPPER(:sex), '%') " +
                    "AND departure_date <= TO_DATE(:departureDate, 'YYYY-MM-DD') " +
                    "ORDER BY " +
                    "CASE WHEN :orderBy = 'function' THEN function END, " +
                    "CASE WHEN :orderBy = 'lastname' THEN lastname END, " +
                    "CASE WHEN :orderBy = 'firstname' THEN firstname END, " +
                    "CASE WHEN :orderBy = 'sex' THEN sex END " +
                    "DESC",
            nativeQuery = true
    )
    List<Employee> findAllByCriteriaBeforeDepartureWithSortDesc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate,
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

    default List<Employee> findAllByCriteriaAfterStartWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String orderBy,
            String direction
    ) {
        if (direction.equals("ASC")) {
            return findAllByCriteriaAfterStartWithSortAsc(function, lastname, firstname, sex, startDate, orderBy);
        }
        return findAllByCriteriaAfterStartWithSortDesc(function, lastname, firstname, sex, startDate, orderBy);
    }

    default List<Employee> findAllByCriteriaBeforeDepartureWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate,
            String orderBy,
            String direction
    ) {
        if (direction.equals("ASC")) {
            return findAllByCriteriaBeforeDepartureWithSortAsc(function, lastname, firstname, sex, departureDate, orderBy);
        }
        return findAllByCriteriaBeforeDepartureWithSortDesc(function, lastname, firstname, sex, departureDate, orderBy);
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
}
