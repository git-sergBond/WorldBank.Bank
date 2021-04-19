package api.resource;

import api.dto.AuthPasswordDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthApi {

    /**
     * ###
     * POST http://localhost:8080/auth/login
     * Content-Type: application/json
     *
     * {
     *   "login": "albatross11",
     *   "password": "123"
     * }
     * @param authPasswordDto
     * @return
     */
    @PostMapping("/auth/login")
    boolean login(@RequestBody AuthPasswordDto authPasswordDto);
}
