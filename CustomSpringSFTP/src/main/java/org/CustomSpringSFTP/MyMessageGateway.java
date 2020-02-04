package org.CustomSpringSFTP;

import java.io.File;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MyMessageGateway {

	@Gateway(requestChannel = "uploadFile")
	public void uploadFile(File file);
}
