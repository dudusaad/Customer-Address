package com.example.request.Controller;

import com.example.request.Models.Customer;
import com.example.request.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/getCustomers", "/{documentId}"})
    public ResponseEntity<List<Customer>> getCustomer(@PathVariable(required = false) Long documentId){
        List<Customer> customers = new ArrayList<>();
        customerService.getCustomersDetails(documentId).forEach(customers::add);

        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id){
        try {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ResponseBody
    @PostMapping(path = "/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        try{
            Customer customerCreated = customerService.save(customer);
            return new ResponseEntity<>(customerCreated, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{customerId}/address/{addressId}")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable("customerId") Long customerId, @PathVariable("addressId") Long addressId){
        try{
            Customer customerUpdated = customerService.assignAddressToCustomer(customerId, addressId);
            if(customerUpdated != null){
                return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
