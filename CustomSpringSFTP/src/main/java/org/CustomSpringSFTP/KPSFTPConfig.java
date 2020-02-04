package org.CustomSpringSFTP;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageHandler;

@Configuration
public class KPSFTPConfig {

	private DefaultSftpSessionFactory kpSessionFactory() {
		DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
		factory.setHost("0.0.0.0");
		factory.setPort(22);
		factory.setAllowUnknownKeys(true);
		factory.setUser("userName");
		factory.setPassword("password");
		return factory;
	}
	
	@Bean
	@ServiceActivator(inputChannel = "uploadFile")
	MessageHandler uploadHandler() {
		SftpMessageHandler messageHandler = new SftpMessageHandler(kpSessionFactory());
		messageHandler.setRemoteDirectoryExpression(new LiteralExpression("/upload"));
		messageHandler.setFileNameGenerator(message -> String.format("mytestfile.txt", LocalDateTime.now()));
		return messageHandler;
	}
}
