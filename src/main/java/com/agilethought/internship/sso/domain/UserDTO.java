package com.agilethought.internship.sso.domain;

import io.swagger.models.auth.In;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class UserDTO {

	@Id
	private String id;

	private Integer type;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private Integer status;

	public UserDTO(Integer type, String firstName, String lastName, String email, String password, Integer status) {

		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.status = status;
	}

}
