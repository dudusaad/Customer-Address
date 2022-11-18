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

    public List<Address> getAddressesDetails(Long customerId){
        if(customerId != null){
            return addressRepository.findAllById(Collections.singleton(customerId));
        }
        return addressRepository.findAll();
    }

    public void deleteById(Long id){
        addressRepository.deleteById(id);
    }

    public Address save(Address address){
        return addressRepository.save(address);
    }

    public Address update(Long id, Address address){
        Optional<Address> addressFound = addressRepository.findById(id);
        if(addressFound.isPresent()){
            addressFound.get().setId(id);
            addressFound.get().setZipCode(address.getZipCode());
            addressFound.get().setNumber(address.getNumber());
            return addressRepository.save(addressFound.get());
        }
        else {
            return null;
        }
    }

    public Address findByZipCodeAndNumber(Long zipCode, int number){
        return addressRepository.findByZipCodeAndNumber(zipCode, number);
    }
}
