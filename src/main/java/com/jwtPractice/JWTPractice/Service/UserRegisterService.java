package com.jwtPractice.JWTPractice.Service;

import com.jwtPractice.JWTPractice.Repository.UserRepo;
import com.jwtPractice.JWTPractice.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    public UserRepo userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepo.save(users);
    }
}
