package com.programming.techie.Spring_Blog.services;

import com.programming.techie.Spring_Blog.model.Role;
import com.programming.techie.Spring_Blog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role){
        Optional<Role> role1 = roleRepository.findById(role.getRoleName());
        if (role1.isEmpty()){
            Role newRole = roleRepository.save(role);
            return newRole;
        }else {
            System.out.println("The provided Role name already exists");
            return null;

        }
    }
}
