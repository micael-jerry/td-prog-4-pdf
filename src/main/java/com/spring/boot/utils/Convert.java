package com.spring.boot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.model.Employee;

import java.util.List;

public class Convert {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String employeeListAsString(List<Employee> employees) throws JsonProcessingException {
        return objectMapper.writeValueAsString(employees);
    }

    public static List<Employee> stringAsEmployeeList(String employees) throws JsonProcessingException {
        return objectMapper.readValue(
                employees,
                new TypeReference<List<Employee>>() {}
        );
    }
}
