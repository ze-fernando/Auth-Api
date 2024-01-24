package com.zefernando.PaymentPix.service;

import com.zefernando.PaymentPix.entity.User;
import com.zefernando.PaymentPix.repository.UserRepository;
import com.zefernando.PaymentPix.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        if(userRepo.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists");
        }
        else{
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationToken(randomCode);
            user.setIsActive(false);
            User savedUser = userRepo.save(user);
            return savedUser;
        }
    }
}
