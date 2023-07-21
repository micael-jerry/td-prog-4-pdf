package com.spring.boot.service;

import com.spring.boot.model.Cin;
import com.spring.boot.repository.CinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CinService {
    private CinRepository cinRepository;

    public Cin save(Cin cin) {
        return cinRepository.save(cin);
    }
}
