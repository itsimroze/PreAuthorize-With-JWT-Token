package com.example.jwtImplementation.jwtimpl.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class AuthResponse {
    String token;

    public AuthResponse(){

    }

    public AuthResponse(String token){
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
