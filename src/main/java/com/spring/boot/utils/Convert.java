package com.spring.boot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.model.Employee;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public class Convert {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String employeeListAsString(List<Employee> employees) throws JsonProcessingException {
        return objectMapper.writeValueAsString(employees);
    }

    public static List<Employee> stringAsEmployeeList(String employees) throws JsonProcessingException {
        return objectMapper.readValue(
                employees,
                new TypeReference<List<Employee>>() {
                }
        );
    }

    public static Date stringToDateAndFormat(String stringDate) {
//        Format: yyyy-MM-dd
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            log.error("Date convert error");
            return null;
        }
    }

    public static String dateToStringAndFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}