package com.example.webapp.resource;

import api.dto.AccountDto;
import api.resource.AccauntApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AccountResource implements AccauntApi {

    @Override
    public AccountDto getAccount(@PathVariable Long accountId) {
        return null;
    }
}
