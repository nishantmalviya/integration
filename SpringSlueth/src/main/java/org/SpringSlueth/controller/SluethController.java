package org.SpringSlueth.controller;

import org.SpringSlueth.model.Person;
import org.jboss.logging.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SluethController {

	@GetMapping("/person")
	public Person greeting(@RequestParam(value = "firstName") String firstName,@RequestParam(value = "lastName") String lastName,@RequestParam(value = "address") String address) {
		log.info("Request to get Person");
		System.out.println(MDC.getMap());
		return new Person(firstName,lastName,address);
	}

}
