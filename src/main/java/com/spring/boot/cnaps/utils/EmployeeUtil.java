package com.spring.boot.cnaps.utils;

public class EmployeeUtil {
    public static String formatPersonnelNumber(Integer number) {
        return String.format("EMP%05d", number);
    }
}
