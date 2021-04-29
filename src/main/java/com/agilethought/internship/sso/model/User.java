package com.agilethought.internship.sso.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "users") 
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private Integer type;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private Integer status;

}