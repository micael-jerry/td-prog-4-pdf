package com.spring.boot.employee.repository;

import com.spring.boot.employee.model.Cin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinRepository extends JpaRepository<Cin, Integer> {
    Cin getByCinNumber(String number);
}
