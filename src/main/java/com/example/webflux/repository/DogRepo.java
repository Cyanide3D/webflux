package com.example.webflux.repository;

import com.example.webflux.model.Dog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DogRepo extends ReactiveCrudRepository<Dog, Long> {


}
