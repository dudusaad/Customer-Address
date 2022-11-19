package com.example.request.Controller;

import com.example.request.Models.Address;
import com.example.request.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = {"/getAllAddresses", "/{addressId}"})
    public ResponseEntity<List<Address>> getAddresses(@PathVariable(required = false) Long addressId) {
        List<Address> addresses = addressService.getAddressesDetails(addressId);
        if (addresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Address address : addresses) {
            address.setZipCode(address.maskZipCode(address.getZipCode()));
        }
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        try {
            addressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/create")
    @ResponseBody
    public ResponseEntity<Address> create(@RequestBody Address address) {
        try {
            if (address.validateZipCodeMask(address.getZipCode())) {
                address.setZipCode(address.replaceZipCode(address.getZipCode()));
                if (addressService.findByZipCodeAndNumber(address.getZipCode(), address.getNumber()) == null) {
                    Address addressCreated = addressService.save(address);
                    addressCreated.setZipCode(address.maskZipCode(addressCreated.getZipCode()));
                    return new ResponseEntity<>(addressCreated, HttpStatus.CREATED);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
