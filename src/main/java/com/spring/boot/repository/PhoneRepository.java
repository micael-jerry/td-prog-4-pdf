package com.spring.boot.repository;

import com.spring.boot.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    Phone getByNumber(String number);
}
