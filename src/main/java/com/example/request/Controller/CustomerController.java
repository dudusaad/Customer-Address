package com.example.request.Controller;

import com.example.request.Models.Customer;
import com.example.request.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/getAllCustomers", "/{documentId}"})
    public ResponseEntity<List<Customer>> getCustomer(@PathVariable(required = false) Long documentId) {
        try {
            List<Customer> customers = customerService.getCustomersDetails(documentId);
            formatZipCode(customers);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/zipCode/{zipCode}")
    public ResponseEntity<List<Customer>> getCustomerByZipCode(@PathVariable("zipCode") String zipCode) {
        try {
            List<Customer> customers = customerService.getCustomersByZipCode(zipCode);
            formatZipCode(customers);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        try {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @PostMapping(value = {"/create", "/create/address/{addressId}"})
    public ResponseEntity<Customer> create(@RequestBody Customer customer, @PathVariable(required = false) Long addressId) {
        try {
            Customer customerCreated = customerService.save(customer, addressId);
            if (customerCreated.getAddresses() != null) {
                formatZipCode(Collections.singletonList(customerCreated));
            }
            return new ResponseEntity<>(customerCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{customerId}/address/{addressId}")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("addressId") Long addressId) {
        try {
            Customer customerAssign = customerService.assignAddressToCustomer(customerId, addressId);
            formatZipCode(Collections.singletonList((customerAssign)));
            return new ResponseEntity<>(customerAssign, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Long customerId,
                                                   @RequestBody Customer customer) {
        try {
            Customer customerUpdated = customerService.updateCustomer(customerId, customer);
            formatZipCode(Collections.singletonList((customerUpdated)));
            return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void formatZipCode(List<Customer> customers) {
        customers.forEach(customer -> customer.getAddresses().forEach(address -> address.
                setZipCode(address.maskZipCode(address.getZipCode()))));
    }
}
