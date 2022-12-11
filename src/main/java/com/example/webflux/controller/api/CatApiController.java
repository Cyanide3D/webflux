package com.example.webflux.controller.api;

import com.example.webflux.model.Cat;
import com.example.webflux.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cat")
public class CatApiController {

    @Autowired
    private CatService service;

    @GetMapping
    public Flux<String> getImageUrl() {
        return service.urls();
    }

    @GetMapping("/one")
    public Mono<String> getRandomImageUrl() {
        return service.getRandom();
    }

    @PostMapping
    public void addImage(@RequestBody Cat cat) {
        service.save(Mono.just(cat));
    }

}
