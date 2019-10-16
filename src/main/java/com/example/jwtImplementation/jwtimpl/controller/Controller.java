package com.example.jwtImplementation.jwtimpl.controller;

import com.example.jwtImplementation.jwtimpl.configuration.UserReactiveRepsoitory;
import com.example.jwtImplementation.jwtimpl.configuration.UserService;
import com.example.jwtImplementation.jwtimpl.data.AuthRequest;
import com.example.jwtImplementation.jwtimpl.data.AuthResponse;
import com.example.jwtImplementation.jwtimpl.data.Message;
import com.example.jwtImplementation.jwtimpl.data.User;
import com.example.jwtImplementation.jwtimpl.jwtutils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @Autowired
    private JWTUtils jwtUtil;

    @Autowired
    private UserService userRepository;

    @Autowired
    UserReactiveRepsoitory userReactiveRepsoitory;

    @Autowired
    UserService userService;

    @PostMapping("/controller/signUp")
    public Mono<User> signUp(@RequestBody User user){
        return userReactiveRepsoitory.save(user);
    }

    @GetMapping("/controller/getAll")
    public Flux<User> getAllData(){
        return userReactiveRepsoitory.findAll();
    }

    @PostMapping("/controller/getToken")
    public Mono<AuthResponse> getToken(@RequestBody User user){
        return userService.getMyToken(user);
    }

    @GetMapping("/getData")
    @PreAuthorize("hasRole('USER')")
    public Mono<Message> helloWorld(){
        return Mono.just(new Message("Hello World"));
    }

    @PostMapping("/signIn")
    @PreAuthorize("hasRole('DESIGNER')")
    public Mono<AuthResponse> login(@RequestBody AuthRequest ar) {

        /*return userRepository.findByUsername(ar.getUsername()).map((userDetails) -> {
            if (ar.getPassword().equals(userDetails.getPassword())) {
                return new AuthResponse(jwtUtil.generateToken(userDetails));
            } else {
                return new AuthResponse("Access Denied");
            }
        }).defaultIfEmpty(new AuthResponse("Pass a token"));*/
        return Mono.just(new AuthResponse("Hello world"));
    }
}