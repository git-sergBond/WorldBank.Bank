package com.example.webapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
public class Client {

    @Id
    @SequenceGenerator(name = "ClientIdSeq", sequenceName = "SEQ_client_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClientIdSeq")
    Long id;

    @NotNull
    String first_name;

    @NotNull
    String middle_name;

    @NotNull
    String last_name;

    @NotNull
    Instant birthday;

    @NotNull
    String address;


    String mobile_plone;


    String email;
    
    String passwd;
}
