package com.resources.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.resources.model.User;

@Repository
public interface RepositoryApplication extends MongoRepository<User, String> {

}
