package com.example.jwtImplementation.jwtimpl.configuration;

import com.example.jwtImplementation.jwtimpl.data.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserReactiveRepsoitory extends ReactiveMongoRepository<User, String> {
    @Override
    Mono<User> findById(String s);
}
