package com.agilethought.internship.sso.domain;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class UserDTO {

	@Id
	private String id;

	private String name;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private Integer status;

	public UserDTO(String name, String firstName, String lastName, String email, String password, Integer status) {

		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.status = status;
	}

}
