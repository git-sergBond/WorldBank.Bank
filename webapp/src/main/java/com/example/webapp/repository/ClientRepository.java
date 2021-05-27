package com.example.webapp.repository;

import com.example.webapp.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
