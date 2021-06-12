package com.example.webapp.service;

import com.example.webapp.domain.Client;
import com.example.webapp.mappper.ClientMapper;
import com.example.webapp.openapi.model.ClientDto;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.repository.ClientRepository;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthentificationService {

    private final ClientRepository clientRepository;

    public ClientDto login(EmailPasswordDto pair) {
        if (pair == null
                || StringUtils.isBlank(pair.getEmail())
                || StringUtils.isBlank(pair.getPassword())) {
            throw new IllegalArgumentException("Wrong arguments");
        }

        Client client = clientRepository.findByEmail(pair.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (client.getPasswd().equals(pair.getPassword())) {
            return ClientMapper.INSTANCE.toDto(client);
        }

        throw  new IllegalArgumentException("Incorrect password");
    }
}
