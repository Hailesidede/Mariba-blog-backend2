package com.programming.techie.Spring_Blog.services;

import com.programming.techie.Spring_Blog.model.JwtRequest;
import com.programming.techie.Spring_Blog.model.JwtResponse;
import com.programming.techie.Spring_Blog.model.User;
import com.programming.techie.Spring_Blog.repository.UserRepository;
import com.programming.techie.Spring_Blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private AuthenticationManager authenticationManager;


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName,userPassword);
        final UserDetails userDetails = loadUserByUsername(userName);
        String newGenerateToken = jwtUtil.generateToken(userDetails);
        User user = userRepository.findById(userName).get();
        JwtResponse jwtResponse = new JwtResponse(
                user,newGenerateToken
        );
        return jwtResponse;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).get();
        if (user != null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthorities(user)
            );

        }else{
            throw new UsernameNotFoundException("Username is not valid ");

        }

    }


    private Set getAuthorities (User user){
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });

        return authorities;
    }


    private void authenticate (String userName , String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

        }catch (DisabledException e){
            throw new Exception("User is Disabled ");
        }catch (BadCredentialsException e){
            throw  new Exception( "Bad Credentials From User");
        }


    }
}
