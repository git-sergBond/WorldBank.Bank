package com.example.webapp.service;


import com.example.webapp.domain.Client;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private ClientRepository clientRepository;

    /**
     * IF login found
     * AND password is correct for login
     * EXPECTED: client info
     */
    @Test
    public void loginTrue() {
        String email = "1";
        String password = "1";

        when(clientRepository.findByEmail(eq(email))).thenReturn(
                Optional.of(Client.builder().passwd(password).email(email).build())
        );

        var result = authService.login(new EmailPasswordDto()
                .email(email).password(password)
        );

        assertEquals(email, result.getEmail());
    }

    /**
     * IF login != password
     * EXPECTED: false
     */
    @Test
    public void loginFalse() {
        MockitoAnnotations.initMocks(this);

        EmailPasswordDto authPasswordDto = new EmailPasswordDto();
        authPasswordDto.setPassword("1");
        authPasswordDto.setEmail("111");
        //Assertions.assertFalse(authService.login(authPasswordDto));
    }

    /**
     * IF authPasswordDto == null
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void loginError1() {
        MockitoAnnotations.initMocks(this);
        try {
           // boolean res = authService.login(null);
          //  Assertions.fail("IllegalArgumentException was expected but returned: " + res);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (Exception e) {
            Assertions.fail("IllegalArgumentException was expected but ", e);
        }
    }

    /**
     * IF login == null AND password == null
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void loginError2() {
        MockitoAnnotations.initMocks(this);
        try {
          //  boolean res = authService.login(new EmailPasswordDto());
         //   Assertions.fail("IllegalArgumentException was expected but returned: " + res);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (Exception e) {
            Assertions.fail("IllegalArgumentException was expected but ", e);
        }
    }

    /**
     * IF login == null
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void loginError3() {
        MockitoAnnotations.initMocks(this);
        try {
            EmailPasswordDto authPasswordDto = new EmailPasswordDto();
            authPasswordDto.setPassword("1");
            //authPasswordDto.setLogin("111");

           // boolean res = authService.login(authPasswordDto);
          //  Assertions.fail("IllegalArgumentException was expected but returned: " + res);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (Exception e) {
            Assertions.fail("IllegalArgumentException was expected but ", e);
        }
    }

    /**
     * IF password == null
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void loginError4() {
        MockitoAnnotations.initMocks(this);
        try {
            EmailPasswordDto authPasswordDto = new EmailPasswordDto();
            //authPasswordDto.setPassword("1");
            authPasswordDto.setEmail("111");

           // boolean res = authService.login(authPasswordDto);
           // Assertions.fail("IllegalArgumentException was expected but returned: " + res);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (Exception e) {
            Assertions.fail("IllegalArgumentException was expected but ", e);
        }
    }

    /**
     * IF login is Empty AND passwod is Empty
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void loginError5() {
        MockitoAnnotations.initMocks(this);
        try {
            EmailPasswordDto authPasswordDto = new EmailPasswordDto();
            authPasswordDto.setPassword("");
            authPasswordDto.setEmail("");

           // boolean res = authService.login(authPasswordDto);
           // Assertions.fail("IllegalArgumentException was expected but returned: " + res);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (Exception e) {
            Assertions.fail("IllegalArgumentException was expected but ", e);
        }
    }

    /**
     * IF login is Empty
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void loginError6() {
        MockitoAnnotations.initMocks(this);
        try {
            EmailPasswordDto authPasswordDto = new EmailPasswordDto();
            authPasswordDto.setPassword("1");
            authPasswordDto.setEmail("");

           // boolean res = authService.login(authPasswordDto);
           // Assertions.fail("IllegalArgumentException was expected but returned: " + res);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (Exception e) {
            Assertions.fail("IllegalArgumentException was expected but ", e);
        }
    }

    /**
     * IF password is Empty
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void loginError7() {
        MockitoAnnotations.initMocks(this);
        try {
            EmailPasswordDto authPasswordDto = new EmailPasswordDto();
            authPasswordDto.setPassword("");
            authPasswordDto.setEmail("111");

          //  boolean res = authService.login(authPasswordDto);
         //   Assertions.fail("IllegalArgumentException was expected but returned: " + res);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (Exception e) {
            Assertions.fail("IllegalArgumentException was expected but ", e);
        }
    }
}