package com.example.webapp.service;

import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.repository.ClientRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private ClientRepository clientRepository;

    public AuthService (ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean login(EmailPasswordDto pair) {
        if (pair == null
                || StringUtils.isBlank(pair.getEmail())
                || StringUtils.isBlank(pair.getPassword())) {
            throw new IllegalArgumentException("wrong arguments");
        }
        return clientRepository.findByEmail(pair.getEmail())
                .map(client -> client.getPasswd().equals(pair.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
