package com.example.webapp.resource;

import api.dto.AuthPasswordDto;
import api.resource.AuthApi;
import com.example.webapp.service.AuthService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AuthResource implements AuthApi {

    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean login(@RequestBody AuthPasswordDto authPasswordDto) {
        return authService.login(authPasswordDto);
    }
}
