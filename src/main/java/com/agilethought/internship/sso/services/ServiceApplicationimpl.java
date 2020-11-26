package com.agilethought.internship.sso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.agilethought.internship.sso.ATSSOApplication;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.mapper.UserTransformer;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import com.agilethougth.intership.sso.errorhandling.HttpExceptionMessage;
import com.agilethougth.intership.sso.errorhandling.PathErrorMessage;
import java.util.List;

@Service
@Slf4j
public class ServiceApplicationimpl implements ServiceApplication {

	@Autowired
	private UserTransformer userTransformer;

	@Autowired
	private RepositoryApplication repositoryApplication;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserId createUser(UserDTO userDTO) {
		UserId userId = new UserId();
		userDTO.setStatus(PopulateFields.StatusPopulation(userDTO.getStatus()));
		if (!BusinessValidations.emptyNullFirstName(userDTO.getFirstName())) {
			if (!BusinessValidations.emptyNullLastName(userDTO.getLastName())) {
				userDTO.setName(PopulateFields.NamePopulation(userDTO.getName(), userDTO.getFirstName(),
						userDTO.getLastName()));
				if (!BusinessValidations.emptyNullWrongEmail(userDTO.getEmail())) {
					if (!BusinessValidations.emptyNullPassword(userDTO.getPassword())) {
						List<UserDTO> users = repositoryApplication
								.findUsersByEmail(userDTO.getEmail().toLowerCase().trim());
						if (users.isEmpty()) {
							if (userDTO.getId() != null)
								userDTO.setId(null);
							User user = userTransformer.transformer(userDTO);
							user.setPassword(passwordEncoder.encode(user.getPassword()));
							String userIdDb = repositoryApplication.save(user).getId();
							log.info("Created sucessfully on mongoDB");
							userId.setId(userIdDb);
							return userId;
						} else
							throw new BadRequestException(HttpExceptionMessage.BadRequestMailAlreadyExists,
									PathErrorMessage.pathApi, HttpStatus.BAD_REQUEST);
					}
				}
			} else {
				ATSSOApplication.logger.info("Error");
			}
		}
		return userId;
	}

	@Override
	public List<UserDTO> getUsers() {
		log.info("ServiceApplicationimpl.getUsers - Before getting all the users");
		List<User> response = repositoryApplication.findAll();
		log.info("ServiceApplicationimpl.getUsers -  Consulted successfully on mongoDB: {}", response);
		return userTransformer.listTransformer(response);
	}

	@Override
	public List<UserDTO> getUsersByEmail(String email) {
		log.info("ServiceApplicationimpl.getUsers - Searching users by email");
		List<UserDTO> users = repositoryApplication.findUsersByEmail(email);
		log.info("ServiceApplicationimpl.getUsersByEmail - getUsersByEmail operation was successful: {}", users);
		return users;
	}

}// End class
