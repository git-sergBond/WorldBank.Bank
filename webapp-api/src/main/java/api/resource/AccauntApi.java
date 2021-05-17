package api.resource;

import api.dto.AccountDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AccauntApi {

    @GetMapping("/ext/account/{accountId}")
    AccountDto getAccount(@PathVariable Long accountId);
}
