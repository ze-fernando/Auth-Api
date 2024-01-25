package com.zefernando.PaymentPix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String verificationToken;

    private Boolean isActive;

    public User(Long id, String name, String email, String password, String verificationToken, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.verificationToken = verificationToken;
        this.isActive = isActive;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}