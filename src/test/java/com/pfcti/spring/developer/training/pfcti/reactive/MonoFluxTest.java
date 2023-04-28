package com.pfcti.spring.developer.training.pfcti.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class MonoFluxTest {
    @Test
    public void givenMonoPublisher_whenSubscribeThenReturnSingleValue() {
        Mono<String> helloMono = Mono.just("Hello");
        StepVerifier.create(helloMono)
                .expectNext("Hello")
                .expectComplete()
                .verify();
    }

    @Test
    public void givenFluxPublisher_whenSubscribedThenReturnMultipleValues() {
        Flux<String> stringFlux = Flux.just("Hello", "Spring Course");
        StepVerifier.create(stringFlux)
                .expectNext("Hello")
                .expectNext("Spring Course")
                .expectComplete()
                .verify();
    }

    @Test
    public void givenFluxPublisher_whenSubscribedThenReturnMultipleIntegers() {
        Flux<Integer> integerFlux = Flux.just(1,2,3,4,5);
        StepVerifier.create(integerFlux)
                .expectNext(1).expectNext(2)
                .expectNext(3).expectNext(4)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    @Test
    public void givenFluxPublisher_whenSubscribeThenReturnMultipleValuesWithError() {
        Flux<String> stringFlux = Flux.just("Hello", "Spring course", "Error")
                .map(str -> {
                    System.out.println("Recibe del flujo: " + str);
                    if (str.equals("Error"))
                        throw new RuntimeException("Throwing Error");
                    return str; }).map(str -> str.concat("2")).delayElements(Duration.ofSeconds(3));
        StepVerifier.create(stringFlux)
                .expectNext("Hello2")
                .expectNext("Spring course2")
                .expectError()
                .verify(); }
}
