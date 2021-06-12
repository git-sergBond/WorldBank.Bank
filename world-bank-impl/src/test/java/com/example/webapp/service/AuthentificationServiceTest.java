package com.example.webapp.service;


import com.example.webapp.domain.Client;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthentificationServiceTest {

    @InjectMocks
    private AuthentificationService authentificationService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void loginCorrect() {
        String email = "1";
        String password = "1";

        when(clientRepository.findByEmail(eq(email))).thenReturn(
                Optional.of(Client.builder().passwd(password).email(email).build())
        );

        var result = authentificationService.login(new EmailPasswordDto()
                .email(email).password(password)
        );

        assertEquals(email, result.getEmail());
    }

    @Test
    public void loginIncorrect() {
        String email = "1";
        String password = "1";
        String realPassword = "2";

        when(clientRepository.findByEmail(eq(email))).thenReturn(
                Optional.of(Client.builder().passwd(realPassword).email(email).build())
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(new EmailPasswordDto()
                    .email(email).password(password)
            );
        });

        assertEquals("Incorrect password", exception.getMessage());
    }

    @Test
    public void loginEmptyEmailPasswordDto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(null);
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void loginNullEmailOrPasswordCase1() {
        String email = null;
        String password = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(new EmailPasswordDto()
                    .email(email).password(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void loginNullEmailOrPasswordCase2() {
        String email = "1";
        String password = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(new EmailPasswordDto()
                    .email(email).password(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void loginNullEmailOrPasswordCase3() {
        String email = null;
        String password = "1";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(new EmailPasswordDto()
                    .email(email).password(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void loginEmptyEmailOrPasswordCase1() {
        String email = "";
        String password = "";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(new EmailPasswordDto()
                    .email(email).password(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void loginEmptyEmailOrPasswordCase2() {
        String email = "";
        String password = "1";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(new EmailPasswordDto()
                    .email(email).password(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void loginEmptyEmailOrPasswordCase3() {
        String email = "1";
        String password = "";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authentificationService.login(new EmailPasswordDto()
                    .email(email).password(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }
}