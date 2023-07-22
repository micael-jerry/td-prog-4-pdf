package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.PhoneDto;
import com.spring.boot.model.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PhoneMapper {

    public List<Phone> toEntity(String phones) {
        List<Phone> phoneList = new ArrayList<>();
        Arrays.stream(phones
                        .replaceAll("\\s+", "")
                        .split(","))
                .forEach(phone -> {
                    phoneList.add(new Phone(null, phone, null));
                });
        return phoneList;
    }

    public PhoneDto fromEntity(Phone phone) {
        return PhoneDto.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .build();
    }

    public List<PhoneDto> fromEntity(List<Phone> phones) {
        return phones.stream().map(this::fromEntity).toList();
    }
}
