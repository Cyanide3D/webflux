package com.example.webflux.model;

import org.springframework.data.annotation.Id;

public class Dog {

    @Id
    private Long id;
    private String url;
    private String name;

    public Dog() {
    }

    public Dog(String url) {
        this.url = url;
    }

    public Dog(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
