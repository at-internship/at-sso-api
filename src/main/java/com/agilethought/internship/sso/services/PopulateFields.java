package com.agilethought.internship.sso.services;

public class PopulateFields {

	public static String NamePopulation(String name, String firstName, String lastName) {

		if (name != null && !name.isEmpty()) {
			return name;
		} else {
			return name = firstName + " " + lastName;
		}
	}

	public static Integer StatusPopulation(Integer status) {
		
		if (status != null && status == 0) {
			return status;
		} else {
			return status = 1;
		}
	}

}
