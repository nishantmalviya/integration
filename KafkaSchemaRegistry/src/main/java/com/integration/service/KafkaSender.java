package com.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.integration.config.Configurations;
import com.integration.model.User;

@Service
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void send(User user){
        kafkaTemplate.send(Configurations.TOPIC, user);
    }
}