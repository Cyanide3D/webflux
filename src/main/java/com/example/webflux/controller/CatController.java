package com.example.webflux.controller;

import com.example.webflux.model.Cat;
import com.example.webflux.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatService service;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("urls", service.urls());
        return "cat/index";
    }

    @PostMapping
    public String addImage(@ModelAttribute Cat cat) {
        service.save(Mono.just(cat));
        return "redirect:/cat";
    }

}
