package com.example.webapp.service;

import api.dto.AuthPasswordDto;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean login(AuthPasswordDto authPasswordDto) {
        if (authPasswordDto == null) return false;
        if (authPasswordDto.getLogin() == null || authPasswordDto.getLogin().isEmpty()) return false;
        if (authPasswordDto.getPassword() == null || authPasswordDto.getPassword().isEmpty()) return false;
        return "albatross11".equals(authPasswordDto.getLogin()) && "123".equals(authPasswordDto.getPassword());
    }
}
