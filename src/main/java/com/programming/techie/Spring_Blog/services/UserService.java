package com.programming.techie.Spring_Blog.services;

import com.programming.techie.Spring_Blog.model.Role;
import com.programming.techie.Spring_Blog.model.User;
import com.programming.techie.Spring_Blog.repository.RoleRepository;
import com.programming.techie.Spring_Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepository roleRepository;

    public User registerNewUser(User user){
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        if (user1.isEmpty()){
            Role role = roleRepository.findById("User").get();
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            User newUser = userRepository.save(user);
            return newUser;
        }else {
            System.out.println("User Email already Exists!!");
            return null;
        }

    }


    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);


        Role userRole = new Role();
        userRole.setRoleName("User");
        adminRole.setRoleDescription("User role");
        roleRepository.save(userRole);


        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setEmail("admin@email");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);


        User user = new User();
        user.setUserFirstName("user");
        user.setUserLastName("user");
        user.setUserName("user123");
        user.setUserPassword(getEncodedPassword("user@pass"));
        user.setEmail("user@email");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        userRepository.save(user);
    }


    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
