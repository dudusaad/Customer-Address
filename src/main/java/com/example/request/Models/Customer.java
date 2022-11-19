package com.example.request.Models;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "documentId", nullable = false, unique = true)
    private Long documentId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "registrationDate")
    private Date registrationDate;

    @Column(name = "lastUpdateInfo")
    private Date lastUpdateInfo;

    @ManyToMany
    @JoinTable(name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id",
                    referencedColumnName = "id"))
    private Set<Address> addresses;

    @PrePersist
    protected void onCreate() {
        registrationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateInfo = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(id, customer.id) && Objects.equals(documentId, customer.documentId) && Objects.equals(name, customer.name) && Objects.equals(registrationDate, customer.registrationDate) && Objects.equals(lastUpdateInfo, customer.lastUpdateInfo) && Objects.equals(addresses, customer.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentId, name, age, registrationDate, lastUpdateInfo, addresses);
    }
}
