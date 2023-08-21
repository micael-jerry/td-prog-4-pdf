package com.spring.boot.employee.model;

import com.spring.boot.employee.utils.EmployeeUtil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


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

    @Column(unique = true, nullable = false)
    private Integer endToEndId;

    @Column(unique = true)
    private String personnelNumber;

    @Column(length = 100)
    private String firstname;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Transient
    private String cnapsNumber;

    private Integer childrenCount;

    @Enumerated(EnumType.STRING)
    private SocioProfessionalCategory socioProfessionalCategory;

    @Column(nullable = false)
    private String function;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @Column(unique = true)
    private Integer id_image;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToOne
    @JoinColumn(nullable = false)
    private Cin cin;

    @OneToOne
    @JoinColumn(unique = true)
    private Email personalEmail;

    @OneToOne
    @JoinColumn(unique = true)
    private Email professionalEmail;

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private List<Phone> phones;

    @PostPersist
    private void createPersonnelNumber() {
        this.personnelNumber = EmployeeUtil.formatPersonnelNumber(this.id);
    }
}
