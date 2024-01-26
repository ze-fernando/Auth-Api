package com.zefernando.PaymentPix.dto;

public record UserResponse(
        Long id,
        String name,
        String email,
        String password
) {
}
