package com.example.webapp.resource;

import com.example.webapp.openapi.api.AuthentificationApi;
import com.example.webapp.openapi.model.EmailPasswordDto;
import com.example.webapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
public class Auth23Resource {//implements AuthentificationApi {

    private final AuthService authService;

    public Auth23Resource(AuthService authService) {
        this.authService = authService;
    }

    //@Override
    @PostMapping("signIn")
    public ResponseEntity<Boolean> signIn(@RequestBody EmailPasswordDto emailPasswordDto) {
        return new ResponseEntity<>(authService.login(emailPasswordDto), HttpStatus.OK);
    }
}
