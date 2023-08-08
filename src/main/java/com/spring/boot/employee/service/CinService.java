package com.spring.boot.employee.service;

import com.spring.boot.employee.model.Cin;
import com.spring.boot.employee.repository.CinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CinService {
    private CinRepository cinRepository;

    public Cin save(Cin cin) {
        return cinRepository.save(cin);
    }

    public Cin update(Cin cin) {
        return cinRepository.save(cin);
    }
}
