package com.example.webflux.controller.api;

import com.example.webflux.model.Dog;
import com.example.webflux.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dog")
public class DogApiController {

    @Autowired
    private DogService service;

    @GetMapping
    public Flux<String> getImageUrl() {
        return service.urls();
    }

    @GetMapping("/one")
    public Mono<String> getRandomImageUrl() {
        return service.getRandom();
    }

    @PostMapping
    public void addImage(@RequestBody Dog dog) {
        service.save(Mono.just(dog));
    }

}
