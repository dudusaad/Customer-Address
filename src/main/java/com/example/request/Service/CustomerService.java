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

            return customerRepository.save(customerDb.get());
        }
        return null;
    }

    public Customer assignAddressToCustomer(Long customerId, Long addressId) {
        Set<Address> addressSet = null;
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Address> address = addressService.findById(addressId);
        if(customer.isPresent() && address.isPresent()){
            addressSet = customer.get().getAddresses();
            addressSet.add(address.get());
            customer.get().setAddresses(addressSet);
            return customerRepository.save(customer.get());
        }
       return null;
    }
}
