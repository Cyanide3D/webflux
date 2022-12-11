package com.example.webflux.controller;

import com.example.webflux.model.Cat;
import com.example.webflux.model.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cat", new Cat());
        model.addAttribute("dog", new Dog());
        return "index";
    }

}
