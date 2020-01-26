package com.integration.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.integration.config.Configurations;
import com.integration.model.User;

@Service
public class MyKafkaConsumer {

    @KafkaListener(topics = Configurations.TOPIC, groupId = Configurations.GROUP)
    public void consume(User message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message.getName()));
    }
}
