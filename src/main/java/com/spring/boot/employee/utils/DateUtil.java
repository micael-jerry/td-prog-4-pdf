package com.spring.boot.employee.utils;

import java.util.Date;

public class DateUtil {

    public static int ageCalculator(Date birthdate) {
        Date now = new Date();
        long diff = now.getYear() - birthdate.getYear();

        Date birthdateInYear = new Date(now.getYear(), birthdate.getMonth(), birthdate.getDate());
        if (birthdateInYear.before(now)) {
            diff--;
        }
        return (int) diff;
    }
}