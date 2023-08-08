package com.spring.boot.employee.repository;

import com.spring.boot.employee.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
    Email getByAddress(String address);
}
