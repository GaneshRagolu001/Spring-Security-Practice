package com.jwtPractice.JWTPractice.Controller;

import com.jwtPractice.JWTPractice.Service.UserRegisterService;
import com.jwtPractice.JWTPractice.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {

    @Autowired
    public UserRegisterService userRegisterService;

    @PostMapping("/register")
    public Users register(@RequestBody Users users){

        return userRegisterService.register(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){
        System.out.println(users);
        return userRegisterService.verifylogin(users);
    }
}
