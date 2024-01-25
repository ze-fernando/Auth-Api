package com.zefernando.PaymentPix.dto;

import com.zefernando.PaymentPix.entity.User;

public record UserRequest(
        String name,
        String email,
        String password
){

    public User toModel(){
        return new User(name, email, password);
    }
}
