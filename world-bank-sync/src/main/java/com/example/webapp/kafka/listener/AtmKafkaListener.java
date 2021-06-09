package com.example.webapp.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class AtmKafkaListener {

    //TODO change consumer group
    @KafkaListener(topics = "atm-request")//TODO get this strings from .properties
    public void payListener(String message) {
        System.out.println("!!!" + message);
    }

}
