package com.example.webapp.resource;

import api.dto.AuthPasswordDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthResource {

    /**
     * ###
     * POST http://localhost:8080/login
     * Content-Type: application/json
     *
     * {
     *   "login": "albatross1",
     *   "password": "123"
     * }
     * @param authPasswordDto
     * @return
     */
    @PostMapping("login")
    boolean login(@RequestBody AuthPasswordDto authPasswordDto) {
        if (authPasswordDto == null) return false;
        if (authPasswordDto.getLogin() == null || authPasswordDto.getLogin().isEmpty()) return false;
        if (authPasswordDto.getPassword() == null || authPasswordDto.getPassword().isEmpty()) return false;
        return "albatross".equals(authPasswordDto.getLogin()) && "123".equals(authPasswordDto.getPassword());
    }
}
