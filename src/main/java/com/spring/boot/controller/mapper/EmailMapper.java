package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.EmailDto;
import com.spring.boot.controller.dto.UpdateEmployeeDto;
import com.spring.boot.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public Email toEntity(Integer id, String address) {
        Email email = new Email();
        email.setId(id);
        email.setAddress(address);
        return email;
    }

    public EmailDto fromEntity(Email email) {
        return EmailDto.builder()
                .id(email.getId())
                .address(email.getAddress())
                .build();
    }
}
