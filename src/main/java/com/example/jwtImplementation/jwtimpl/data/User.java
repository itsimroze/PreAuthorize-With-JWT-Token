package com.example.jwtImplementation.jwtimpl.data;

import com.example.jwtImplementation.jwtimpl.enumData.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
