package com.example.request.Service;

import com.example.request.Models.Address;
import com.example.request.Models.Customer;
import com.example.request.Repository.AddressRepository;
import com.example.request.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Customer> getCustomersDetails(Long documentId) {
        if (documentId != null) {
            return customerRepository.findAllByDocumentId(documentId);
        } else {
            return customerRepository.findAll();
        }
    }

    public List<Customer> getCustomersByZipCode(String zipCode) {
        if (zipCode != null) {
            return customerRepository.findAllByAddresses_ZipCode(zipCode).stream().distinct().collect(Collectors.toList());
        } else {
            return customerRepository.findAll();
        }
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer save(Customer customer, Long addressId) {
        if (addressId != null) {
            Set<Address> addressSet = new HashSet<>();
            Address address = addressRepository.findById(addressId).get();
            addressSet.add(address);
            customer.setAddresses(addressSet);
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer customerDb = customerRepository.findById(customerId).get();
        if (customerDb != null) {
            customerDb.setName(customer.getName());
            customerDb.setDocumentId(customer.getDocumentId());
            customerDb.setAge(customer.getAge());

            return customerRepository.save(customerDb);
        }
        return customerDb;
    }

    public Customer assignAddressToCustomer(Long customerId, Long addressId) {
        Set<Address> addressSet = null;
        Customer customer = customerRepository.findById(customerId).get();
        Address address = addressRepository.findById(addressId).get();
        addressSet = customer.getAddresses();
        addressSet.add(address);
        customer.setAddresses(addressSet);
        return customerRepository.save(customer);
    }
}
