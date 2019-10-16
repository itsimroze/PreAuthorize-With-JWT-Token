package com.example.jwtImplementation.jwtimpl.jwtutils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwtImplementation.jwtimpl.enumData.Role;

public class JWTExtract {
    public static String getEmail(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getHeaderClaim("username").toString();
    }

    public static Role getRole(String token){
        DecodedJWT jwt = JWT.decode(token);

        String role = jwt.getHeaderClaim("role").toString();
        if (role.equals("USER")){
            return Role.ROLE_USER;
        }
        else return Role.ROLE_DESIGNER;
    }
}
