package com.agilethought.internship.sso.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	List<User> findAll();

	UserDTO findById();

	@Query("{ 'email' : ?0 }")
	List<UserDTO> findUsersByEmail(String email);

	boolean existsByEmail(String email);
	
	@Query("{ 'email' : ?0, 'password' : ?1 }")
	List<User> findUserWithCredentials(String email, String password);

}
