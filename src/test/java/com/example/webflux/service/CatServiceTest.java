package com.example.webflux.service;

import com.example.webflux.controller.api.CatApiController;
import com.example.webflux.model.Cat;
import com.example.webflux.repository.CatRepo;
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
@WebFluxTest(controllers = CatApiController.class)
@Import(CatService.class)
class CatServiceTest {

    @MockBean
    private CatRepo repo;

    @MockBean
    private CatService service;

    @Autowired
    private WebTestClient webClient;

    @Test
    void urls() {
        Flux<String> flux = Flux.just("url123");

        Mockito
                .when(service.urls())
                .thenReturn(flux);

        webClient.get().uri("/api/cat")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class);

        Mockito.verify(service, times(1)).urls();
    }

    @Test
    void save() {
        Cat cat = new Cat();
        cat.setName("name");
        cat.setUrl("url123");

        webClient.post()
                .uri("/api/cat")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(cat))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getRandom() {
        List<Cat> list = List.of(new Cat("url1", "name1"), new Cat("url2", "name2"));

        Mockito.when(service.getRandom())
                .thenReturn(Mono.just(list.get(new Random().nextInt(list.size())).getUrl()));

        webClient.get().uri("/api/cat/one")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class);

        Mockito.verify(service, times(1)).getRandom();
    }
}