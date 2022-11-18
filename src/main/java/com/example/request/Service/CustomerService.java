package com.example.request.Service;

import com.example.request.Models.Address;
import com.example.request.Models.Customer;
import com.example.request.Repository.AddressRepository;
import com.example.request.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Customer> getCustomersDetails(Long documentId){
        if(documentId != null){
            return customerRepository.findAllByDocumentId(documentId);
        }
        else {
            return customerRepository.findAll();
        }
    }
    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer assignAddressToCustomer(Long customerId, Long addressId){
        Set<Address> addressSet = null;
        Customer customer = customerRepository.findById(customerId).get();
        Address address = addressRepository.findById(addressId).get();
        addressSet = customer.getAddresses();
        addressSet.add(address);
        customer.setAddresses(addressSet);
        return customerRepository.save(customer);
    }
}
