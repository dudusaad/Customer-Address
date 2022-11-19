package com.example.request.Service;

import com.example.request.Models.Address;
import com.example.request.Models.Customer;
import com.example.request.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressService addressService;

    public List<Customer> getCustomersDetails(Long documentId) {
        List<Customer> customers;
        if (documentId != null) {
            customers = customerRepository.findAllByDocumentId(documentId);
        } else {
            customers = customerRepository.findAll();
        }
        if(customers.isEmpty()){
            throw new IllegalArgumentException("Customer not found");
        }
        return customers;
    }

    public List<Customer> getCustomersByZipCode(String zipCode) {
        List<Customer> customers;
        if (zipCode != null) {
            customers =  customerRepository.findAllByAddresses_ZipCode(zipCode).stream().distinct().collect(Collectors.toList());
        } else {
            customers = customerRepository.findAll();
        }
        if(customers.isEmpty()){
            throw new IllegalArgumentException("Customers not found");
        }
        return customers;
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer save(Customer customer, Long addressId) {
        if (addressId != null) {
            Set<Address> addressSet = new HashSet<>();
            Optional<Address> address = addressService.findById(addressId);
            address.ifPresent(addressSet::add);
            customer.setAddresses(addressSet);
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer customer) {
        Optional<Customer> customerDb = customerRepository.findById(customerId);
        if (customerDb.isPresent()) {
            customerDb.get().setName(customer.getName());
            customerDb.get().setDocumentId(customer.getDocumentId());
            customerDb.get().setAge(customer.getAge());
        }
        return customerRepository.save(customerDb.get());
    }

    public Customer assignAddressToCustomer(Long customerId, Long addressId) {
        Set<Address> addressSet;
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Address> address = addressService.findById(addressId);
        if(customer.isPresent() && address.isPresent()){
            addressSet = customer.get().getAddresses();
            addressSet.add(address.get());
            customer.get().setAddresses(addressSet);
        }
        return customerRepository.save(customer.get());
    }
}
