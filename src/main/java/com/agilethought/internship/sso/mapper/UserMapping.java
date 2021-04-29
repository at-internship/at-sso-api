package com.agilethought.internship.sso.mapper;

import com.agilethought.internship.sso.dto.NewUserResponse;
import org.springframework.stereotype.Component;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.model.User;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;


@Component
public class UserMapping implements OrikaMapperFactoryConfigurer {
	
	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(User.class, NewUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, UserDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(UserDTO.class, User.class).mapNulls(false).byDefault().register();
//		orikaMapperFactory.classMap(User.class, UpdateUserResponse.class).mapNulls(false).byDefault().register();
//		orikaMapperFactory.classMap(User.class, LoginResponse.class).mapNulls(false).byDefault().register();

	}
}
