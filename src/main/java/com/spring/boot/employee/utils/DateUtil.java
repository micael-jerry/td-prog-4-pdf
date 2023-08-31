package com.spring.boot.employee.utils;

import com.spring.boot.employee.model.AgeParamType;

import java.util.Date;

public class DateUtil {

    public static int ageCalculator(Date birthdate, AgeParamType ageParamType) {
        Date now = new Date();
        long diff = now.getYear() - birthdate.getYear();

        if (ageParamType == AgeParamType.YEAR_ONLY) {
            return (int) diff;
        }

        Date birthdateInYear = new Date(now.getYear(), birthdate.getMonth(), birthdate.getDate());
//      (now.getMonth() < birthdate.getMonth()) || (now.getMonth() == birthdate.getMonth() && now.getDate() < birthdate.getDate())
        if (now.before(birthdateInYear)) {
            diff--;
        }
        return (int) diff;
    }
}
