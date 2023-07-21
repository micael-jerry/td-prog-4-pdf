package com.spring.boot.model;

import com.spring.boot.utils.EmployeeUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

//        TODO: Téléphones : un employé peut avoir plusieurs
//        TODO: adresse exacte,
//        TODO: email perso et email pro
//        TODO: fonction au sein de l’entreprise,
//        TODO: nombre d’enfants à sa charge,
//        TODO: date de son embauche et la date de son départ.
//        TODO: sa catégorie socio-professionnelle, voir  http://bitly.ws/LEL4 pour plus de détails.
//        TODO: son numéro CNAPS (alphanumérique)

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

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(unique = true)
    private Integer id_image;

    @OneToOne
    @JoinColumn(nullable = false)
    private Cin cin;

    @PostPersist
    private void createPersonnelNumber() {
        this.personnelNumber = EmployeeUtil.formatPersonnelNumber(this.id);
    }
}
