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
        if (addressId != null) {
            return addressRepository.findAllById(Collections.singleton(addressId));
        }
        return addressRepository.findAll();
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

    public Optional<Address> findById(Long addressId){
        return addressRepository.findById(addressId);
    }
}
