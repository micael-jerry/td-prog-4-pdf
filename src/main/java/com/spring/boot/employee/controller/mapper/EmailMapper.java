package com.spring.boot.employee.controller.mapper;

import com.spring.boot.employee.controller.dto.EmailDto;
import com.spring.boot.employee.model.Email;
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
