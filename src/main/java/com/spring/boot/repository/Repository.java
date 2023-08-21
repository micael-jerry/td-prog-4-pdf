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

    List<Employee> findAllByCriteriaWithSort(
            String function,
            String lastname,
            String firstname,
            String sex,
            String orderBy,
            String direction
    );

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
