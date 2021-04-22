package com.agilethought.internship.sso.mapper;

import java.util.List;

import com.agilethought.internship.sso.domain.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

@Component
public class UserTransformer {

	private MapperFacade mapperFacade;

	@Autowired
	public void transform(MapperFactory factory) {
		this.mapperFacade = factory.getMapperFacade();
		factory.classMap(User.class, UserDTO.class).mapNulls(false).mapNullsInReverse(false).byDefault().register();
		factory.classMap(UserDTO.class, User.class).mapNulls(false).mapNullsInReverse(false).byDefault().register();
		factory.classMap(UserDTO.class, User.class).mapNulls(false).mapNullsInReverse(false).byDefault().register();
	}

	public User transformer(UserDTO userDTO) {
		return mapperFacade.map(userDTO, User.class);
	}

	public UserDTO transformer(User user) {
		return mapperFacade.map(user, UserDTO.class);
	}

	public List<UserDTO> listTransformer(List<User> users) {
		return mapperFacade.mapAsList(users, UserDTO.class);
	}

}
