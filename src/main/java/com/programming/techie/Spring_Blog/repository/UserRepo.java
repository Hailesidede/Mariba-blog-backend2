package com.programming.techie.Spring_Blog.repository;

import com.programming.techie.Spring_Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
