package com.agilethought.internship.sso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.mapper.UserTransformer;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.repository.RepositoryApplication;
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
		if (userDTO.getId() != null)
			userDTO.setId(null);

		User user = userTransformer.transformer(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String userIdDb = repositoryApplication.save(user).getId();
		log.info("Created sucessfully on mongoDB");
		UserId userId = new UserId();
		userId.setId(userIdDb);
		return userId;
	}
	
	@Override
	public List<UserDTO> getUsers() {
		List<User> response = repositoryApplication.findAll();
		log.info("Consulted sucessfully on mongoDB");
		return userTransformer.listTransformer(response);
	}

	@Override
	public List<UserDTO> getUsersByEmail(String email) {
		List<UserDTO> users = repositoryApplication.findUsersByEmail(email);
		return users;
	}

}// End class
