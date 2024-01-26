package com.zefernando.PaymentPix.controller;

import com.zefernando.PaymentPix.dto.UserRequest;
import com.zefernando.PaymentPix.dto.UserResponse;
import com.zefernando.PaymentPix.entity.User;
import com.zefernando.PaymentPix.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {
        User user = userRequest.toModel();
        UserResponse userSaved = userService.registerUser(user);
        return ResponseEntity.ok().body(userSaved);
    }

    @GetMapping("verify/")
    public String verifyCode(@Param("code") String code){
        if(userService.verify(code)){
            return "User active";
        } else{
            return "User is not active";
        }
    }
}
