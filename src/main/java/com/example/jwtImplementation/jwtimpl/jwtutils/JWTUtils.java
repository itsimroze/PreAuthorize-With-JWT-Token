package com.example.jwtImplementation.jwtimpl.jwtutils;

import com.example.jwtImplementation.jwtimpl.Constant;
import com.example.jwtImplementation.jwtimpl.data.User;
import com.example.jwtImplementation.jwtimpl.enumData.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;


@Component
public class JWTUtils implements Serializable {

    private static final long serialVersionUID = 1L;

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(Constant.secret.getBytes())).parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);
        claims.put("role", roles);
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

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}


