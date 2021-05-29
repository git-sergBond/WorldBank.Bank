package com.example.webapp.service;

import com.example.webapp.mappper.ClientMapper;
import com.example.webapp.openapi.model.ClientDto;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.repository.ClientRepository;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final ClientRepository clientRepository;

    public ClientDto login(EmailPasswordDto pair) {
        if (pair == null
                || StringUtils.isBlank(pair.getEmail())
                || StringUtils.isBlank(pair.getPassword())) {
            throw new IllegalArgumentException("wrong arguments");
        }
        return clientRepository.findByEmail(pair.getEmail())
                .filter(client -> client.getPasswd().equals(pair.getPassword()))
                .map(ClientMapper.INSTANCE::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
