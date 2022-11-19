package com.example.request.Service;

import com.example.request.Models.Address;
import com.example.request.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddressesDetails(Long addressId) {
        List<Address> addresses;
        if (addressId != null) {
            addresses = addressRepository.findAllById(Collections.singleton(addressId));
        } else {
            addresses = addressRepository.findAll();
        }
        return addresses;
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address findByZipCodeAndNumber(String zipCode, int number) {
        return addressRepository.findByZipCodeAndNumber(zipCode, number);
    }

    public Optional<Address> findById(Long addressId) {
        return addressRepository.findById(addressId);
    }
}
