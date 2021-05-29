package com.example.webapp.resource;

import com.example.webapp.openapi.api.AuthentificationApi;
import com.example.webapp.openapi.model.ClientDto;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationResource implements AuthentificationApi {

    private final AuthService authService;

    public AuthentificationResource(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<ClientDto> signIn(@RequestBody EmailPasswordDto emailPasswordDto) {
        boolean r = authService.login(emailPasswordDto);
        ClientDto c = new ClientDto();
        if (r) {
            c.setEmail(emailPasswordDto.getEmail());
        }
        return new ResponseEntity<ClientDto>(c, null, HttpStatus.OK);
    }
}
