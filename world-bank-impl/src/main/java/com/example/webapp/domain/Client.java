package com.example.webapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @SequenceGenerator(name = "ClientIdSeq", sequenceName = "SEQ_client_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClientIdSeq")
    Long id;

    @NotNull
    String firstName;

    @NotNull
    String middleName;

    @NotNull
    String lastName;

    @NotNull
    Instant birthday;

    @NotNull
    String address;

    @Column(name = "mobile_plone")//TODO rename column
    String mobilePhone;


    String email;
    
    String passwd;
}
