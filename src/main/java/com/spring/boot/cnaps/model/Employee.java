package com.spring.boot.cnaps.model;

import com.spring.boot.employee.utils.EmployeeUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String personnelNumber;

    @Column(length = 100)
    private String firstname;

    @Column(nullable = false, length = 100)
    private String lastname;

    @PostPersist
    private void createPersonnelNumber() {
        this.personnelNumber = EmployeeUtil.formatPersonnelNumber(this.id);
    }
}
