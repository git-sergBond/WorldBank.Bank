package com.example.webapp.service;

import com.example.webapp.domain.Client;
import com.example.webapp.openapi.model.ClientDto;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void registerSuccess() {

        String email = "1";

        when(clientRepository.findByEmail(eq(email))).thenReturn(Optional.empty());

        when(clientRepository.save(any())).thenReturn(Client.builder().email(email).build());

        var res = registrationService.register(new ClientDto()
                .email(email)
                .passwd("2")
                .address("asd")
                .birthday("1998-02-01T21:00:00Z")
                .firstName("asd")
                .lastName("last")
                .middleName("middle")
                .phone("8 800")
        );

        assertNotNull(res);
        assertEquals(email, res.getEmail());
    }

    @Test
    void registerErrorUserAlreadyExist() {
        String email = "1";

        when(clientRepository.findByEmail(eq(email))).thenReturn(Optional.of(new Client()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.register(new ClientDto()
                    .email(email)
                    .passwd("2")
                    .address("asd")
                    .birthday("1998-02-01T21:00:00Z")
                    .firstName("asd")
                    .lastName("last")
                    .middleName("middle")
                    .phone("8 800")
            );
        });

        assertEquals("This email already registered", exception.getMessage());
    }

    @Test
    public void registerNullEmailOrPasswordCase1() {
        String email = null;
        String password = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.register(new ClientDto()
                    .email(email).passwd(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void registerNullEmailOrPasswordCase2() {
        String email = "1";
        String password = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.register(new ClientDto()
                    .email(email).passwd(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void registerNullEmailOrPasswordCase3() {
        String email = null;
        String password = "1";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.register(new ClientDto()
                    .email(email).passwd(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void registerEmptyEmailOrPasswordCase1() {
        String email = "";
        String password = "";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.register(new ClientDto()
                    .email(email).passwd(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void registerEmptyEmailOrPasswordCase2() {
        String email = "";
        String password = "1";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.register(new ClientDto()
                    .email(email).passwd(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }

    @Test
    public void registerEmptyEmailOrPasswordCase3() {
        String email = "1";
        String password = "";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registrationService.register(new ClientDto()
                    .email(email).passwd(password)
            );
        });

        assertEquals("Wrong arguments", exception.getMessage());
    }
}