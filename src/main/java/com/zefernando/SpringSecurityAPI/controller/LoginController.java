package com.zefernando.SpringSecurityAPI.controller;

import com.zefernando.SpringSecurityAPI.dto.AuthRequest;
import com.zefernando.SpringSecurityAPI.dto.AuthResponse;
import com.zefernando.SpringSecurityAPI.entity.User;
import com.zefernando.SpringSecurityAPI.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest authRequest){
        var userAndPassword = new UsernamePasswordAuthenticationToken(
                authRequest.email(),
                authRequest.password()
        );
        var auth = authenticationManager.authenticate(userAndPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
