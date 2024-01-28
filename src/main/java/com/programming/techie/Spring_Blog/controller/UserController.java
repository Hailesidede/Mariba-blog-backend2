package com.programming.techie.Spring_Blog.controller;

import com.programming.techie.Spring_Blog.model.User;
import com.programming.techie.Spring_Blog.repository.RoleRepository;
import com.programming.techie.Spring_Blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);

    }


    @PostConstruct
    public void initRolesAndUser(){
        userService.initRolesAndUser();
    }


    @GetMapping({"/forAdmin"})
    public String forAdmin(){
        return "This Url is only accessible for admin Only";
    }


    @GetMapping({"/forUser"})
    public String forUser(){
        return "This Url is only accessible to the User";
    }

}
