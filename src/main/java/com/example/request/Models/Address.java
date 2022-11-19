package com.example.request.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "zipCode", nullable = false)
    private String zipCode;

    @Column(name = "number")
    private int number;

    @JsonIgnore
    @ManyToMany(mappedBy = "addresses")
    private Set<Customer> customers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number && zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, number);
    }

    public boolean validateZipCodeMask(String zipCode){
        String regex = "^[0-9]{5}(?:-[0-9]{3})?$";
        return zipCode.matches(regex);
    }

    public String maskZipCode(String zipCode){
        return zipCode.replaceFirst("(\\d{5})(\\d{3})", "$1-$2");
    }

    public String replaceZipCode(String zipCode){
        return zipCode.replaceAll("-", "");
    }

}
