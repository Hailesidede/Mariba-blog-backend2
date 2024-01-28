package com.programming.techie.Spring_Blog.repository;

import com.programming.techie.Spring_Blog.model.PostImage;
import com.programming.techie.Spring_Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Integer> {


}
