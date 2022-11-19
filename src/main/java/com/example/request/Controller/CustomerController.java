package com.example.request.Controller;

import com.example.request.Models.Customer;
import com.example.request.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/getCustomers", "/{documentId}"})
    public ResponseEntity<List<Customer>> getCustomer(@PathVariable(required = false) Long documentId) {
        List<Customer> customers = customerService.getCustomersDetails(documentId);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        formatZipCode(customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(path = "/getCustomerByZipCode/{zipCode}")
    public ResponseEntity<List<Customer>> getCustomerByZipCode(@PathVariable("zipCode") String zipCode) {

        List<Customer> customers = customerService.getCustomersByZipCode(zipCode);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        formatZipCode(customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        try {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PostMapping(value = {"/create", "/create/{addressId}"})
    public ResponseEntity<Customer> create(@RequestBody Customer customer, @PathVariable(required = false) Long addressId) {
        try {
            Customer customerCreated = customerService.save(customer, addressId);
            formatZipCode(customerCreated);
            return new ResponseEntity<>(customerCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{customerId}/address/{addressId}")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("addressId") Long addressId) {
        try {
            Customer customerAssign = customerService.assignAddressToCustomer(customerId, addressId);
            if (customerAssign != null) {
                formatZipCode(customerAssign);
                return new ResponseEntity<>(customerAssign, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable("customerId") Long customerId,
                                                            @RequestBody Customer customer) {
        try {
            Customer customerUpdated = customerService.updateCustomer(customerId, customer);
            if (customerUpdated != null) {
                formatZipCode(customerUpdated);
                return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void formatZipCode(List<Customer> customers){
       customers.forEach(customer -> customer.getAddresses().forEach(address -> address.
                setZipCode(address.maskZipCode(address.getZipCode()))));
    }

    private void formatZipCode(Customer customer){
        if(customer.getAddresses() != null) {
            customer.getAddresses().forEach(address -> address.setZipCode(address.maskZipCode(address.getZipCode())));
        }
    }
}
