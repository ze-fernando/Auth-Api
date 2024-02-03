package com.zefernando.SpringSecurityAPI.dto;

public record UserResponse(
        Long id,
        String name,
        String email,
        String password
) {
}
