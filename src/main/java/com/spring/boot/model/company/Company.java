package com.spring.boot.model.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String name;

    private String description;

    private String slogan;

    private CompanyAddress address;

    private String email;

    private List<String> phones;

    private byte[] logo;
}