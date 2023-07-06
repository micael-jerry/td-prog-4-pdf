package com.spring.boot.service.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.boot.model.Employee;
import com.spring.boot.utils.Convert;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.spring.boot.utils.SessionAndModelAttributeName.EMPLOYEE_LIST_ATTRIBUTE;

@Service
public class EmployeeSessionService {
    public List<Employee> findAll(HttpSession session) throws JsonProcessingException {
        this.initEmployeeDataInSession(session);
        return Convert.stringAsEmployeeList((String) session.getAttribute(EMPLOYEE_LIST_ATTRIBUTE));
    }

    public Employee save(HttpSession session, Employee employee) throws JsonProcessingException {
        List<Employee> employees = this.findAll(session);
        employees.add(employee);
        session.setAttribute(EMPLOYEE_LIST_ATTRIBUTE, Convert.employeeListAsString(employees));
        return employee;
    }

    private void initEmployeeDataInSession(HttpSession session) throws JsonProcessingException {
        if (session.getAttribute("employees") == null) {
            List<Employee> newEmployees = new ArrayList<>();
            newEmployees.add(new Employee(null, "a", "fidimalala", new Date()));
            newEmployees.add(new Employee(null, "c", "fidimalala", new Date()));
            session.setAttribute(EMPLOYEE_LIST_ATTRIBUTE, Convert.employeeListAsString(newEmployees));
        }
    }
}
