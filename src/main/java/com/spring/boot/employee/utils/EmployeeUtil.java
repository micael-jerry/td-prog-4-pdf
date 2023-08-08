package com.spring.boot.employee.utils;

public class EmployeeUtil {
    public static String formatPersonnelNumber(Integer number) {
        return String.format("EMP%05d", number);
    }
}
