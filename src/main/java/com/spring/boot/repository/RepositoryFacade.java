package com.spring.boot.repository;


import com.spring.boot.employee.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Primary
@AllArgsConstructor
public class RepositoryFacade implements Repository {
    private final EmployeeRepository employeeRepository;

    private Repository getRepository() {
        return employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return getRepository().save(employee);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return getRepository().findById(id);
    }

    @Override
    public Employee getByCnapsNumber(String cnapsNumber) {
        return getRepository().getByCnapsNumber(cnapsNumber);
    }

    @Override
    public List<Employee> findAllByCountryCode(String countryCode) {
        return getRepository().findAllByCountryCode(countryCode);
    }

    @Override
    public List<Employee> findAllByCriteria(String function, String lastname, String firstname, String sex) {
        return getRepository().findAllByCriteria(function, lastname, firstname, sex);
    }

    @Override
    public List<Employee> findAllByCriteriaWithSort(String function, String lastname, String firstname, String sex, String orderBy, String direction) {
        return getRepository().findAllByCriteriaWithSort(function, lastname, firstname, sex, orderBy, direction);
    }

    @Override
    public List<Employee> findAllByCriteriaAfterStart(String function, String lastname, String firstname, String sex, String startDate) {
        return getRepository().findAllByCriteriaAfterStart(function, lastname, firstname, sex, startDate);
    }

    @Override
    public List<Employee> findAllByCriteriaBeforeDeparture(String function, String lastname, String firstname, String sex, String departureDate) {
        return getRepository().findAllByCriteriaBeforeDeparture(function, lastname, firstname, sex, departureDate);
    }

    @Override
    public List<Employee> findAllByCriteriaBetweenStartAndDeparture(String function, String lastname, String firstname, String sex, String startDate, String departureDate) {
        return getRepository().findAllByCriteriaBetweenStartAndDeparture(function, lastname, firstname, sex, startDate, departureDate);
    }

    @Override
    public List<Employee> findAllByCriteriaAfterStartWithSort(
            String function, String lastname, String firstname, String sex, String startDate, String orderBy, String direction
    ) {
        return getRepository().findAllByCriteriaAfterStartWithSort(function, lastname, firstname, sex, startDate, orderBy, direction);
    }

    @Override
    public List<Employee> findAllByCriteriaBeforeDepartureWithSort(
            String function, String lastname, String firstname, String sex, String departureDate, String orderBy, String direction
    ) {
        return getRepository().findAllByCriteriaBeforeDepartureWithSort(function, lastname, firstname, sex, departureDate, orderBy, direction);
    }

    @Override
    public List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSort(
            String function, String lastname, String firstname, String sex, String startDate, String departureDate, String orderBy, String direction
    ) {
        return getRepository().findAllByCriteriaBetweenStartAndDepartureWithSort(function, lastname, firstname, sex, startDate, departureDate, orderBy, direction);
    }
}