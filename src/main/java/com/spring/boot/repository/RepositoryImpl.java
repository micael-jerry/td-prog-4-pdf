package com.spring.boot.repository;

import com.spring.boot.cnaps.repository.CnapsEmployeeRepository;
import com.spring.boot.employee.model.Employee;
import com.spring.boot.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class RepositoryImpl implements Repository {
    private final CnapsEmployeeRepository cnapsEmployeeRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee save(Employee employee) {
        com.spring.boot.cnaps.model.Employee cnapsEmployee = cnapsEmployeeRepository.getByCnapsNumber(employee.getCnapsNumber());
        employee.setEndToEndId(cnapsEmployee.getId());
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id)
                .map(this::setCnapsNumberByCnapsEmployeeId);
    }

    public Employee getByCnapsNumber(String cnapsNumber) {
        com.spring.boot.cnaps.model.Employee cnapsEmployee = cnapsEmployeeRepository.getByCnapsNumber(cnapsNumber);
        if (cnapsEmployee == null) {
            return null;
        }
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findByEndToEndId(cnapsEmployee.getId())
        );
    }

    @Override
    public List<Employee> findAllByCountryCode(String countryCode) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCountryCode(countryCode)
        );
    }

    @Override
    public List<Employee> findAllByCriteria(
            String function, String lastname, String firstname, String sex
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteria(function, lastname, firstname, sex)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaWithSortAsc(
            String function, String lastname, String firstname, String sex, String orderBy
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaWithSortAsc(function, lastname, firstname, sex, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaWithSortDesc(
            String function, String lastname, String firstname, String sex, String orderBy
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaWithSortDesc(function, lastname, firstname, sex, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaAfterStart(
            String function, String lastname, String firstname, String sex, String startDate
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaAfterStart(function, lastname, firstname, sex, startDate)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBeforeDeparture(
            String function, String lastname, String firstname, String sex, String departureDate
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBeforeDeparture(function, lastname, firstname, sex, departureDate)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBetweenStartAndDeparture(
            String function, String lastname, String firstname, String sex, String startDate, String departureDate
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBetweenStartAndDeparture(function, lastname, firstname, sex, startDate, departureDate)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaAfterStartWithSortAsc(
            String function, String lastname, String firstname, String sex, String startDate, String orderBy
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaAfterStartWithSortAsc(function, lastname, firstname, sex, startDate, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBeforeDepartureWithSortAsc(
            String function, String lastname, String firstname, String sex, String departureDate, String orderBy
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBeforeDepartureWithSortAsc(function, lastname, firstname, sex, departureDate, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSortAsc(
            String function, String lastname, String firstname, String sex, String startDate, String departureDate, String orderBy
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBetweenStartAndDepartureWithSortAsc(function, lastname, firstname, sex, startDate, departureDate, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaAfterStartWithSortDesc(
            String function, String lastname, String firstname, String sex, String startDate, String orderBy) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaAfterStartWithSortDesc(function, lastname, firstname, sex, startDate, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBeforeDepartureWithSortDesc(
            String function, String lastname, String firstname, String sex, String departureDate, String orderBy
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBeforeDepartureWithSortDesc(function, lastname, firstname, sex, departureDate, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSortDesc(
            String function, String lastname, String firstname, String sex, String startDate, String departureDate, String orderBy
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBetweenStartAndDepartureWithSortDesc(function, lastname, firstname, sex, startDate, departureDate, orderBy)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaAfterStartWithSort(
            String function, String lastname, String firstname, String sex, String startDate, String orderBy, String direction
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaAfterStartWithSort(function, lastname, firstname, sex, startDate, orderBy, direction)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBeforeDepartureWithSort(
            String function, String lastname, String firstname, String sex, String departureDate, String orderBy, String direction
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBeforeDepartureWithSort(function, lastname, firstname, sex, departureDate, orderBy, direction)
        );
    }

    @Override
    public List<Employee> findAllByCriteriaBetweenStartAndDepartureWithSort(
            String function, String lastname, String firstname, String sex, String startDate, String departureDate, String orderBy, String direction
    ) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaBetweenStartAndDepartureWithSort(function, lastname, firstname, sex, startDate, departureDate, orderBy, direction)
        );
    }

    private List<Employee> setCnapsNumberByCnapsEmployeeId(List<Employee> employees) {
        return employees.stream()
                .map(this::setCnapsNumberByCnapsEmployeeId)
                .collect(Collectors.toList());
    }

    private Employee setCnapsNumberByCnapsEmployeeId(Employee employee) {
        if (employee == null) {
            return null;
        }
        com.spring.boot.cnaps.model.Employee cnapsEmployee = cnapsEmployeeRepository.findById(employee.getEndToEndId()).get();
        employee.setCnapsNumber(cnapsEmployee.getCnapsNumber());
        return employee;
    }
}
