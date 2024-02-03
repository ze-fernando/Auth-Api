package com.zefernando.SpringSecurityAPI.repository;

import com.zefernando.SpringSecurityAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    User findByVerificationCode(String code);
}
