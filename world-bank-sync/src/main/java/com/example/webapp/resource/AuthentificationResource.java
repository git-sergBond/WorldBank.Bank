package com.example.webapp.resource;

import com.example.webapp.openapi.api.AuthentificationApi;
import com.example.webapp.openapi.model.ClientDto;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.service.AuthentificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthentificationResource implements AuthentificationApi {

    private final AuthentificationService authentificationService;

    @Override
    public ResponseEntity<ClientDto> _signIn(@RequestBody EmailPasswordDto emailPasswordDto) {
        return new ResponseEntity<>(authentificationService.login(emailPasswordDto), null, HttpStatus.OK);
    }
}
