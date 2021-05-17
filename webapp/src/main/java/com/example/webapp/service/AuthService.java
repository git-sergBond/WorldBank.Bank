package com.example.webapp.service;

import api.dto.AuthPasswordDto;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean login(AuthPasswordDto authPasswordDto) {
        if (authPasswordDto == null
                || StringUtils.isBlank(authPasswordDto.getLogin())
                || StringUtils.isBlank(authPasswordDto.getPassword())) {
            throw new IllegalArgumentException("wrong arguments");
        }
        return "albatross11".equals(authPasswordDto.getLogin()) && "123".equals(authPasswordDto.getPassword());
    }
}
