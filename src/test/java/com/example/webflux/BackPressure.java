package com.example.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class BackPressure {

    @Test
    public void backpressure() {
        Flux<Integer> limit = Flux.range(1, 25);

        limit.limitRate(10);
        limit.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Finished!!"),
                subscription -> subscription.request(15)
        );

        StepVerifier.create(limit)
                .expectSubscription()
                .thenRequest(15)
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .expectNext(11, 12, 13, 14, 15)
                .thenRequest(10)
                .expectNext(16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
                .verifyComplete();
    }

}
