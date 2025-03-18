package com.jwtPractice.JWTPractice.Service;

import com.jwtPractice.JWTPractice.Repository.UserRepo;
import com.jwtPractice.JWTPractice.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public JWTService jwtService;

    @Autowired
    public AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    public String verifylogin(Users users) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));


        if(authentication.isAuthenticated()){
            return jwtService.generateToken(users.getUsername());
        }
        return "failed";
    }
}
