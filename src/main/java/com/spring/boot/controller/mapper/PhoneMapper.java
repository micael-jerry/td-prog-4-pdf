package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.PhoneDto;
import com.spring.boot.model.Phone;
import com.spring.boot.utils.PhoneUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneMapper {

    public List<Phone> toEntity(String phones) {
        List<Phone> phoneList = new ArrayList<>();
        PhoneUtil.getCountryCodeAndNumber(phones)
                .forEach(countryCodeAndNumber -> {
                    phoneList.add(new Phone(null, countryCodeAndNumber[0], countryCodeAndNumber[1], null));
                });
        return phoneList;
    }

    public PhoneDto fromEntity(Phone phone) {
        return PhoneDto.builder()
                .id(phone.getId())
                .countryCode(phone.getCountryCode())
                .number(phone.getNumber())
                .build();
    }

    public List<PhoneDto> fromEntity(List<Phone> phones) {
        return phones.stream().map(this::fromEntity).toList();
    }
}
