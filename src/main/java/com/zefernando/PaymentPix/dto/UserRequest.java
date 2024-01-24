package com.zefernando.PaymentPix.dto;

public record UserRequest(
        String name,
        String email,
        String password
){}
