package org.SpringConfigClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConfigurationClient {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ConfigurationClient.class, args);
		ConfigService configService = ctx.getBean(ConfigService.class);
		System.out.println(configService.getServiceName());
	}
}
