package com.spring.boot.employee.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneUtil {

    public static List<String[]> getCountryCodeAndNumber(String phones) {
        String[] phoneArray = phones.replaceAll("\\s+", "").split(",");
//        phone array item value : [country code, number]
        return Arrays.stream(phoneArray)
                .map(phone -> phone.split("-"))
                .collect(Collectors.toList());
    }
}
