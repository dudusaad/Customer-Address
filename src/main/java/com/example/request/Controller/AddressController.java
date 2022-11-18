package com.example.request.Controller;

import com.example.request.Models.Address;
import com.example.request.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = {"/getAddresses", "/{customerId}"})
    public ResponseEntity<List<Address>> getAddresses(@PathVariable(required = false) Long customerId){
        List<Address> address = new ArrayList<>();
        addressService.getAddressesDetails(customerId).forEach(address::add);

        if(address.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id){
        try {
            addressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    @ResponseBody
    public ResponseEntity<Address> create(@RequestBody Address address){
        try{
            if(addressService.findByZipCodeAndNumber(address.getZipCode(), address.getNumber()) == null){
                Address addressCreated = addressService.save(address);
                return new ResponseEntity<>(addressCreated, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
