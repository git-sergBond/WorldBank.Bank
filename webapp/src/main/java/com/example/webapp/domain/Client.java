package com.example.webapp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class Client {

    @Id
    Long id;

    String first_name;

    String middle_name;

    String last_name;

    String passwd;

    Instant birthday;

    String address;

    String mobile_plone;

    String email;
}
