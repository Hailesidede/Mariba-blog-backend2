package com.programming.techie.Spring_Blog.exeception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException (String message){
        super(message);
    }
}
