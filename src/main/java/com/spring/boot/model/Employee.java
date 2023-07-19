package com.spring.boot.model;

import com.spring.boot.utils.EmployeeUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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

    @Column(length = 100)
    private String firstname;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(unique = true)
    private Integer id_image;

    @Column(unique = true)
    private String personnelNumber;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @PostPersist
    private void createPersonnelNumber() {
        this.personnelNumber = EmployeeUtil.formatPersonnelNumber(this.id);
    }
}
