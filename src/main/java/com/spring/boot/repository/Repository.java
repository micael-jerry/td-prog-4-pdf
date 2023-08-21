package com.spring.boot.repository;

import com.spring.boot.employee.model.Employee;

import java.util.List;
import java.util.Optional;

public interface Repository {

    Employee save(Employee employee);

    Optional<Employee> findById(Integer id);

    Employee getByCnapsNumber(String cnapsNumber);

    List<Employee> findAllByCountryCode(String countryCode);

    List<Employee> findAllByCriteria(
            String function,
            String lastname,
            String firstname,
            String sex
    );

    List<Employee> findAllByCriteriaWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String orderBy
    );

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

    List<Employee> findAllByCriteriaAfterStart(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate
    );

    List<Employee> findAllByCriteriaBeforeDeparture(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate
    );

    List<Employee> findAllByCriteriaBetweenStartAndDeparture(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate
    );

    List<Employee> findAllByCriteriaAfterStartWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String orderBy
    );

    List<Employee> findAllByCriteriaBeforeDepartureWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate,
            String orderBy
    );

    List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSortAsc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate,
            String orderBy
    );

    List<Employee> findAllByCriteriaAfterStartWithSortDesc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String orderBy
    );

    List<Employee> findAllByCriteriaBeforeDepartureWithSortDesc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate,
            String orderBy
    );

    List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSortDesc(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate,
            String orderBy
    );

    List<Employee> findAllByCriteriaAfterStartWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String orderBy,
            String direction
    );

    List<Employee> findAllByCriteriaBeforeDepartureWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String departureDate,
            String orderBy,
            String direction
    );

    List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String startDate,
            String departureDate,
            String orderBy,
            String direction
    );
}
