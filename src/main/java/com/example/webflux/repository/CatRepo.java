package com.example.webflux.repository;

import com.example.webflux.model.Cat;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CatRepo extends ReactiveCrudRepository<Cat, Long> {
}
