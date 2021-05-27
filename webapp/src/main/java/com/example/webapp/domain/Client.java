package com.example.webapp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
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

    public Client(String first_name,
                  String middle_name,
                  String last_name,
                  String passwd,
                  Instant birthday,
                  String address,
                  String mobile_plone,
                  String email) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.passwd = passwd;
        this.birthday = birthday;
        this.address = address;
        this.mobile_plone = mobile_plone;
        this.email = email;
    }

    public Client() {

    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", mobile_plone='" + mobile_plone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPasswd() {
        return passwd;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile_plone() {
        return mobile_plone;
    }

    public String getEmail() {
        return email;
    }
}
