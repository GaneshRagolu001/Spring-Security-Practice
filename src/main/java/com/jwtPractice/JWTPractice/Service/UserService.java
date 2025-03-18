package com.jwtPractice.JWTPractice.Service;

import com.jwtPractice.JWTPractice.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    public List<User> store = new ArrayList<>();
        UserService(){
            store.add(new User(UUID.randomUUID().toString(),"ganesh","ganesh01@gmail.com"));
            store.add(new User(UUID.randomUUID().toString(),"hemanth","hemanth4848@gmail.com"));
            store.add(new User(UUID.randomUUID().toString(),"jaswanth","jaswanth11@gmail.com"));
            store.add(new User(UUID.randomUUID().toString(),"adarsh","adarsh22@gmail.com"));
        }


    public List<User> getUsers(){
        return store;
    }

    public User addUser(User user){
        store.add(user);
        return user;
    }
}
