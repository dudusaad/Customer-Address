package com.example.request.Repository;

import com.example.request.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByDocumentId(Long documentId);

    List<Customer> findAllByAddresses_ZipCode(String zipCode);
}
