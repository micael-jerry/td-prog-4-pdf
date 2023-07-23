package com.spring.boot.service;

import com.spring.boot.model.Address;
import com.spring.boot.repository.AddressRepository;
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
