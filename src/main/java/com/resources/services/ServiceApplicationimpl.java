package com.resources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resources.model.User;
import com.resources.model.UserId;
import lombok.extern.slf4j.Slf4j;
import com.resources.repository.RepositoryApplication;

@Service
@Slf4j
public class ServiceApplicationimpl implements ServiceApplication {

	@Autowired
	private RepositoryApplication repositoryApplication;
	
	@Override
	public UserId createUser(User user) {
		String userIdDb = repositoryApplication.save(user).getId();
		log.info("Created sucessfully on mongoDB");
		UserId userId = new UserId();
		userId.setId(userIdDb);
		return userId;
	}

	
}
