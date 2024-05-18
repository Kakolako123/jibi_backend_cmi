package com.example.jibi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String cin; // Assuming CIN is a unique identification number
    private Date birthdate;
    private String phoneNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private PaymentAccount paymentAccount;

    @JsonIgnore
    private String password;
    @Column(updatable = false)
    private LocalDate createdDate;
    private String commercialRn;
    private String patent;

    /*@Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return roles or authorities if needed
        return null;
    }*/

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now(); // Set createdDate automatically on creation
    }

    /*@Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }*/
/*
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }*/


}