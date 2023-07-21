package com.spring.boot.service;

import com.spring.boot.model.Email;
import com.spring.boot.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private EmailRepository emailRepository;

    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public Email update(Email email) {
        return emailRepository.save(email);
    }
}
