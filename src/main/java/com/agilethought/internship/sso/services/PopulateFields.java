package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.domain.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateFields {

	private static String namePopulation(String name, String firstName, String lastName) {

		if (name != null && !name.isEmpty()) {
			return name;
		} else {
			return name = firstName + " " + lastName;
		}
	}

	private static Integer statusPopulation(Integer status) {

		if (status != null && status == 0) {
			return status;
		} else {
			return status = 1;
		}
	}

	public static UserDTO populate(UserDTO userDTO) {
		if (userDTO.getId() != null) {
			log.info("ServiceApplicationimpl.createUser - id exists");
			userDTO.setId(null);
		}

		userDTO.setStatus(statusPopulation(userDTO.getStatus()));
		userDTO.setName(namePopulation(userDTO.getName(), userDTO.getFirstName(), userDTO.getLastName()));
		return userDTO;
	}
}
