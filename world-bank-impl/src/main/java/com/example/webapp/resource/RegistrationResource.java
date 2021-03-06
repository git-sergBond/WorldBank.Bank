package com.example.webapp.resource;

import com.example.webapp.openapi.api.RegistrationApi;
import com.example.webapp.openapi.model.ClientDto;
import com.example.webapp.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationResource implements RegistrationApi {

    private final RegistrationService registrationService;

    @Override
    public ResponseEntity<ClientDto> _apiAuthSignupPost(ClientDto clientDto) {
        return new ResponseEntity<ClientDto>(registrationService.register(clientDto), HttpStatus.OK);
    }
}
