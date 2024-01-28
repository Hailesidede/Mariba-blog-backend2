package com.programming.techie.Spring_Blog.services;

import com.programming.techie.Spring_Blog.config.JwtRequestFilter;
import com.programming.techie.Spring_Blog.model.Post;
import com.programming.techie.Spring_Blog.repository.PostImageRepository;
import com.programming.techie.Spring_Blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private PostImageRepository postImageRepository;


    public Post createPost(Post post)  {
        post.setCreatedOn(Instant.now());
        post.setTitle(post.getTitle());
        post.setContent(post.getContent());
        post.setUsername(JwtRequestFilter.CURRENT_USER);
        post.setCreatedOn(Instant.now());
        post.setStatus("Published");
        return postRepo.save(post);

    }





    public List<Post> getAllPosts(){

        try {
            List<Post> concatPosts= new ArrayList<>();
            List<Post> posts = postRepo.findAll();
            if (posts.size() > 0){
                for (Post conPost : posts){
                    conPost.setContent(getFirst40Words(conPost.getContent()));
                    concatPosts.add(conPost);
                }
                return concatPosts;
            }else return null;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }



    }


    public String getFirst40Words(String paragraph){
        char[] paragraphChar = paragraph.toCharArray();
        if (paragraphChar.length <=200){
            return paragraph;
        }else{
            String words = paragraph.substring(3,201);
            String finalWords = words +"....";
            return finalWords;
        }
    }

//    public List<PostDto> showAllPosts() {
//        List<Post> posts = postRepo.findAll();
//        return posts.stream().map(this::mapFromPostToDto).collect(toList());
//    }


//    private PostDto mapFromPostToDto(Post post){
//        PostDto postDto = new PostDto();
//        postDto.setTitle(post.getTitle());
//        postDto.setContent(post.getContent());
//        postDto.setUsername(post.getUsername());
//
//        return postDto;
//
//    }


//    private Post mapFromDtoToPost(PostDto postDto){
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        User loggedUser = authService.getCurrentUser().orElseThrow(()->
//                new IllegalArgumentException("No current loged User"));
//        post.setUsername(loggedUser.getUsername());
//        post.setCreatedOn(Instant.now());
//        post.setUpdatedOn(Instant.now());
//        return post;
//
//    }


    public Post readSinglePost(Long id) {

        Optional<Post> optPost = postRepo.findById(id);
        if (optPost.isPresent()){
            Post post = optPost.get();
            return post;
        }
        return null;

    }



    public void deletePost(Long id){
        Optional<Post> post = postRepo.findById(id);
        if (post.isPresent()){
            Post post1 = post.get();
            postRepo.delete(post1);
        }
    }



    public Post updatePost(Post post){
        Optional<Post> optPost = postRepo.findById(post.getId());

        if (optPost.isPresent()){
            Post postToUpdate = optPost.get();
            postToUpdate.setContent(post.getContent());
            postToUpdate.setUpdatedOn(Instant.now());
            postToUpdate.setTitle(post.getTitle());
            postToUpdate.setUsername(post.getUsername());
            postToUpdate.setCreatedOn(post.getCreatedOn());

            return postRepo.save(postToUpdate);
        }
        return null;

    }
}
