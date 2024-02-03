package com.zefernando.SpringSecurityAPI.dto;

import com.zefernando.SpringSecurityAPI.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotNull(message = "The field name cannot be null") @NotBlank(message = "The field name cannot be empty")
        String name,

        @NotNull(message = "The field email cannot be null") @NotBlank(message = "The field email cannot be empty")
        String email,

        @NotNull(message = "The field password cannot be null") @NotBlank(message = "The field password cannot be empty")
        @Size(min = 8, message = "The password field must be 8 characters long")
        String password
){

    public User toModel(){
        return new User(name, email, password);
    }
}
