package com.example.webflux.controller;

import com.example.webflux.model.Dog;
import com.example.webflux.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/dog")
public class DogController {

    @Autowired
    private DogService service;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("urls", service.urls());
        return "dog/index";
    }

    @PostMapping
    public String addImage(@ModelAttribute Dog dog) {
        service.save(Mono.just(dog));
        return "redirect:/dog";
    }

}