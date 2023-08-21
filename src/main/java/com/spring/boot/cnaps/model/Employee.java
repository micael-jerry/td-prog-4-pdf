package com.spring.boot.cnaps.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(unique = true)
    private String cnapsNumber;

    private Integer childrenCount;

    @Enumerated(EnumType.STRING)
    private SocioProfessionalCategory socioProfessionalCategory;

    @Column(nullable = false)
    private String function;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToOne
    @JoinColumn(nullable = false)
    private Cin cin;
}
