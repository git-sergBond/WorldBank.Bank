package com.example.webapp.service;

import api.dto.AuthPasswordDto;
import com.example.webapp.repository.ClientRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private ClientRepository clientRepository;

    public AuthService (ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean login(AuthPasswordDto authPasswordDto) {
        if (authPasswordDto == null
                || StringUtils.isBlank(authPasswordDto.getLogin())
                || StringUtils.isBlank(authPasswordDto.getPassword())) {
            throw new IllegalArgumentException("wrong arguments");
        }

        boolean isAuthenticated = clientRepository.findByEmail(authPasswordDto.getLogin())
                .map(client -> client.getPasswd().equals(authPasswordDto.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return isAuthenticated;
    }
}
