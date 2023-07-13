package com.spring.boot.utils;

public class EmployeeUtil {
    public static String formatPersonnelNumber(Integer number) {
        return String.format("EMP%05d", number);
    }
}
