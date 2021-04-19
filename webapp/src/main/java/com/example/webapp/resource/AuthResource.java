package com.example.webapp.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthResource {

    @PostMapping("login")
    boolean login() {
        return true;
    }
}
