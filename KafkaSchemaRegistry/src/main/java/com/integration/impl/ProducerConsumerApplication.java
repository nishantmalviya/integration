package com.integration.impl;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;

import com.integration.config.Configurations;
import com.integration.model.User;

@SpringBootApplication
@ComponentScan(basePackages = "com.integration")
public class ProducerConsumerApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProducerConsumerApplication.class, args);
	}

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("sending message");

		User user = User.newBuilder().setName("John").setAge(25).build();

		kafkaTemplate.send(new ProducerRecord<String, User>(Configurations.TOPIC, user));

		System.out.println("msg sent");

	}
}