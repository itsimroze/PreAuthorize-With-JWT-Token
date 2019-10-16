package com.example.jwtImplementation.jwtimpl.configuration;

import com.example.jwtImplementation.jwtimpl.Constant;
import com.example.jwtImplementation.jwtimpl.data.AuthResponse;
import com.example.jwtImplementation.jwtimpl.data.User;
import com.example.jwtImplementation.jwtimpl.enumData.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserService{

    @Autowired
    private UserReactiveRepsoitory userReactiveRepsoitory;

    public Mono<AuthResponse> getMyToken(User user){
        //return userReactiveRepsoitory.findById(user.getUsername()).flatMap(this::generateToken);
        return Mono.just(new AuthResponse(generateToken(user)));
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", Role.ROLE_USER);
        claims.put("username", user.getUsername());
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        Long expirationTimeLong = Long.parseLong(Constant.expirationTime); //in second

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(Constant.secret.getBytes()))
                .compact();
    }

    /*public Mono<UserData> findByUsername(String username){
        return userReactiveRepsoitory.findById(username).flatMap(user -> Mono.just(user.getUserData()));
    }*/
}
