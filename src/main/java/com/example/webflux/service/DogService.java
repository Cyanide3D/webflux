package com.example.webflux.service;

import com.example.webflux.model.Cat;
import com.example.webflux.model.Dog;
import com.example.webflux.repository.DogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

@Service
public class DogService {

    @Autowired
    private DogRepo repo;

    public Flux<String> urls() {
        return repo.findAll()
                .map(Dog::getUrl);
    }

    public void save(Mono<Dog> dog) {
        dog
                .flatMap(c -> {
                    c.setName(UUID.randomUUID().toString()); //Устанавливаем рандомное имя картинке
                    return Mono.just(c);
                })
                .doOnError(Throwable::printStackTrace) //При ошибке выводит стактрейс
                .flatMap(repo::save) //Сохраняем картинку в бд
                .subscribe(System.out::println); //Терминальная операция, которая триггерит все действия выше, и после выводит сохранившуюся энтити в консоль
    }

    public Mono<String> getRandom() {
        return repo.count().flatMap(c -> repo
                .findAll()
                .onErrorReturn(new Dog()) //При ошибке возвращаем пустой объект
                .elementAt(new Random().nextInt(Integer.parseInt(String.valueOf(c)))) //Берём рандомный элемент и возвращаем
                .map(Dog::getUrl)
        );
    }


}
