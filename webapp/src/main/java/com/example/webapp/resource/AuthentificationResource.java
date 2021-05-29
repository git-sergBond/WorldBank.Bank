package com.example.webapp.resource;

import com.example.webapp.openapi.api.AuthentificationApi;
import com.example.webapp.openapi.model.ClientDto;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthentificationResource implements AuthentificationApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<ClientDto> _signIn(@RequestBody EmailPasswordDto emailPasswordDto) {
        return new ResponseEntity<>(authService.login(emailPasswordDto), null, HttpStatus.OK);
    }
}
