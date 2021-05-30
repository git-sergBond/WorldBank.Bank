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
public class RegistrationService {

    private final ClientRepository clientRepository;

    public ClientDto register(ClientDto clientDto) {
        if (clientDto == null
                || StringUtils.isBlank(clientDto.getEmail())
                || StringUtils.isBlank(clientDto.getPasswd())) {
            throw new IllegalArgumentException("Wrong arguments");
        }

        if(clientRepository.findByEmail(clientDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email already registered");
        }

        var client = ClientMapper.INSTANCE.toModel(clientDto);

        var saved = clientRepository.save(client);

        return ClientMapper.INSTANCE.toDto(saved);
    }
}
