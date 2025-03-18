    package com.jwtPractice.JWTPractice.Service;


    import com.jwtPractice.JWTPractice.Repository.UserRepo;
    import com.jwtPractice.JWTPractice.entity.UserPrinciple;
    import com.jwtPractice.JWTPractice.entity.Users;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    @Service
    public class MyUserDetailsService implements UserDetailsService {

        @Autowired
        public UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Users user = userRepo.findByUsername(username);

            if(user == null){
                System.out.println("User Not found");
                throw new UsernameNotFoundException("User not found");
            }

            return new UserPrinciple(user);
        }
    }
