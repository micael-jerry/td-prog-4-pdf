package com.spring.boot.employee.service;

import com.spring.boot.employee.model.Address;
import com.spring.boot.employee.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    private AddressRepository addressRepository;

    public Address update(Address address) {
        return addressRepository.save(address);
    }
}
