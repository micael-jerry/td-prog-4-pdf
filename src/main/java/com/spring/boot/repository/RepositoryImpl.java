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
    public List<Employee> findAllByCriteriaWithSort(String function, String lastname, String firstname, String sex, String orderBy, String direction) {
        return this.setCnapsNumberByCnapsEmployeeId(
                employeeRepository.findAllByCriteriaWithSort(function, lastname, firstname, sex, orderBy, direction)
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
