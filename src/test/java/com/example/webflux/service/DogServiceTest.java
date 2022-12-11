package com.example.webflux.service;

import com.example.webflux.controller.api.DogApiController;
import com.example.webflux.model.Dog;
import com.example.webflux.repository.DogRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.times;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = DogApiController.class)
@Import(DogService.class)
class DogServiceTest {

    @MockBean
    private DogRepo repo;

    @MockBean
    private DogService service;

    @Autowired
    private WebTestClient webClient;

    @Test
    void urls() {
        Flux<String> flux = Flux.just("url123");

        Mockito
                .when(service.urls())
                .thenReturn(flux);

        webClient.get().uri("/api/dog")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class);

        Mockito.verify(service, times(1)).urls();
    }

    @Test
    void save() {
        Dog dog = new Dog();
        dog.setName("name");
        dog.setUrl("url123");

        webClient.post()
                .uri("/api/dog")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(dog))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getRandom() {
        List<Dog> list = List.of(new Dog("url1", "name1"), new Dog("url2", "name2"));

        Mockito.when(service.getRandom())
                .thenReturn(Mono.just(list.get(new Random().nextInt(list.size())).getUrl()));

        webClient.get().uri("/api/dog/one")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class);

        Mockito.verify(service, times(1)).getRandom();
    }
}