package com.programming.techie.Spring_Blog.model;

public class EntityResponse <T> {

    private String message;
    private Integer HttpStatus;
    private T entity;


    public EntityResponse(String message, Integer httpStatus, T entity) {
        this.message = message;
        HttpStatus = httpStatus;
        this.entity = entity;
    }

    public EntityResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHttpStatus() {
        return HttpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        HttpStatus = httpStatus;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
