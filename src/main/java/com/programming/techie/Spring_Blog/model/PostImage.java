package com.programming.techie.Spring_Blog.model;

import javax.persistence.*;

@Entity
@Table
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    @Column(length = 50000000)
    private byte[] imageByte;


    public PostImage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public PostImage(Integer id, String name, String type, byte[] imageByte) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageByte = imageByte;
    }
}
