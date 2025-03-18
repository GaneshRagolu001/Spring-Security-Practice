package com.jwtPractice.JWTPractice.JWT;

import com.jwtPractice.JWTPractice.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTfilter extends OncePerRequestFilter {

    @Autowired
    public JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader = request.getHeader("Authorization");
       String token = null;
       String username = null;

       if(authHeader != null && authHeader.startsWith("Bearer ")){
           token = authHeader.substring(7);
           username = jwtService.extractUsername(token);
       }

       if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

           UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(username);
           if(jwtService.validateToken(token,userDetails)){
               UsernamePasswordAuthenticationToken AuthToken =
                       new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               AuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(AuthToken);
           }
       }

       filterChain.doFilter(request,response);
    }
}
