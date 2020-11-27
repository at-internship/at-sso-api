package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.domain.UserDTO;

public class PopulateFields {

	private static String NamePopulation(String name, String firstName, String lastName) {

		if (name != null && !name.isEmpty()) {
			return name;
		} else {
			return name = firstName + " " + lastName;
		}
	}

	private static Integer StatusPopulation(Integer status) {

		if (status != null && status == 0) {
			return status;
		} else {
			return status = 1;
		}
	}

	public static void populate(UserDTO userDTO) {
		userDTO.setStatus(PopulateFields.StatusPopulation(userDTO.getStatus()));
		userDTO.setName(
				PopulateFields.NamePopulation(userDTO.getName(), userDTO.getFirstName(), userDTO.getLastName()));
	}
}
