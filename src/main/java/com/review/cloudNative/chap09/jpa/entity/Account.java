package com.review.cloudNative.chap09.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Account extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CreditCard> creditCards = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Address> addresses = new HashSet<>();

    public Account(String accountNumber, Set<Address> addresses) {
        this.accountNumber = accountNumber;
        this.addresses = addresses;
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
