package com.example.webapp.service;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
class AuthServiceTest {

    @InjectMocks
    public AuthService authService;
/*
    @Before
    void before() {
       Mockito.initMocks(this);
    }
*/
    /**
     * IF authPasswordDto == null
     * EXPECTED: IllegalArgumentException
     */
    @Test
    public void login() {
        authService.login(null);
    }
}