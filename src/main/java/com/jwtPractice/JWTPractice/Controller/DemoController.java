package com.jwtPractice.JWTPractice.Controller;

import com.jwtPractice.JWTPractice.Service.UserService;
import com.jwtPractice.JWTPractice.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    public UserService userService;



    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userService.getUsers();
    }

    @GetMapping("/getactiveuser")
    public String getActiveUser(Principal principal, HttpServletRequest request){
        return principal.getName() + "session id " + request.getSession().getId();
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/get-csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
