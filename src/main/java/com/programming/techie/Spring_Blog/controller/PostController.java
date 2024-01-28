package com.programming.techie.Spring_Blog.controller;

import com.programming.techie.Spring_Blog.model.Post;
import com.programming.techie.Spring_Blog.model.PostImage;
import com.programming.techie.Spring_Blog.repository.PostImageRepository;
import com.programming.techie.Spring_Blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostImageRepository postImageRepo;

    @PostMapping(value = {"add"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('Admin')")
    public Post createPost(@RequestPart("post") Post post, @RequestPart("files") MultipartFile[] files) throws IOException {
        try{

            Set<PostImage> postImages = uploadImage(files);
            postImageRepo.saveAll(postImages);
            post.setPostImage(postImages);
            return postService.createPost(post);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;

        }

    }

    public Set<PostImage> uploadImage(MultipartFile[] files) throws IOException {
        Set<PostImage> images = new HashSet<>();
        for (MultipartFile file : files){
            PostImage postImage = new PostImage();
            postImage.setName(file.getName());
            postImage.setType(file.getContentType());
            postImage.setImageByte(file.getBytes());
            images.add(postImage);

        }
        return images;


    }




    @GetMapping("/all")
    public ResponseEntity<List<Post>> showAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Post> getSinglePost(@PathVariable Long id){
        return new ResponseEntity<>(postService.readSinglePost(id),HttpStatus.OK);

    }


    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public Post updatePost(@RequestBody Post post){
        return postService.updatePost(post);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}
