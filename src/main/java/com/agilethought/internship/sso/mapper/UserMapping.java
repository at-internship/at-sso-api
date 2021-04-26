package com.agilethought.internship.sso.mapper;

import com.agilethought.internship.sso.domain.NewUserResponse;
import com.agilethought.internship.sso.domain.UpdateUserResponse;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.User;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class UserMapping implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(com.agilethought.internship.sso.model.User.class, NewUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(com.agilethought.internship.sso.model.User.class, UpdateUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, com.agilethought.internship.sso.model.User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(com.agilethought.internship.sso.model.User.class, UserDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(UserDTO.class, com.agilethought.internship.sso.model.User.class).mapNulls(false).byDefault().register();
	}

}