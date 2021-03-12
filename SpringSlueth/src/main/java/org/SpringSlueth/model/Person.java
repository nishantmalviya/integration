package org.SpringSlueth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
	public Person(String firstName, String lastName, String address) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
	}
	private String firstName;
	private String lastName;
	private String address;
}
