package com.example.webflux.model;

import org.springframework.data.annotation.Id;

public class Cat {

    @Id
    private Long id;
    private String url;
    private String name;

    public Cat() {
    }

    public Cat(String url) {
        this.url = url;
    }

    public Cat(String url, String name) {
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

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
