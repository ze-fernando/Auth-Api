package com.zefernando.SpringSecurityAPI.service;

import com.zefernando.SpringSecurityAPI.dto.UserResponse;
import com.zefernando.SpringSecurityAPI.entity.User;
import com.zefernando.SpringSecurityAPI.repository.UserRepository;
import com.zefernando.SpringSecurityAPI.utils.RandomString;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public UserResponse registerUser(User user) throws MessagingException, UnsupportedEncodingException {
        if(userRepo.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists");
        }
        else{
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setIsActive(false);
            userRepo.save(user);

            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword()
            );
            mailService.sendEmail(user);
            return userResponse;
        }
    }

    public boolean verify(String verificationCode){
        User user = userRepo.findByVerificationCode(verificationCode);

        if(user == null || user.getIsActive()){
            return false;
        } else{
            user.setVerificationCode(null);
            user.setIsActive(true);
            userRepo.save(user);
            return true;
        }

    }
}
