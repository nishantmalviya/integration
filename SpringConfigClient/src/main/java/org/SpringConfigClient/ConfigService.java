package org.SpringConfigClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

	@Autowired
	private ApplicationConfiguration config;
	
//	@Value("${service.name}")
//	private String serviceName;

	public String getServiceName() {
		return " and config value: "+config.getService().getName();
	}
}
