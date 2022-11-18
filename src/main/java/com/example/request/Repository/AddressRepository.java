package com.example.request.Repository;

import com.example.request.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByZipCodeAndNumber(Long zipCode, int number);
}
