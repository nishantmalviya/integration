package org.SpringConfigClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

	@Value("${service.name}")
	private String serviceName;

	public String getServiceName() {
		return serviceName;
	}
}
