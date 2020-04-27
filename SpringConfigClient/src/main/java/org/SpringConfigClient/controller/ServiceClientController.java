package org.SpringConfigClient.controller;

import org.SpringConfigClient.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RefreshScope
public class ServiceClientController {
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	public @ResponseBody String getEmployee() {
		
		return configService.getServiceName();
	}
}
