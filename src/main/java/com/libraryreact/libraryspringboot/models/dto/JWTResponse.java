package com.libraryreact.libraryspringboot.models.dto;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTResponse {
    private String token;
    private Integer id;
    private String username;
    private Set<String> role;
    private String type = "Bearer";

    public JWTResponse(String token, String username, Set<String> role, Integer id) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.id = id;
    }
}