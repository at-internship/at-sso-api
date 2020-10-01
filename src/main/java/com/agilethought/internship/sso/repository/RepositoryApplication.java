package com.agilethought.internship.sso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agilethought.internship.sso.model.User;

@Repository
public interface RepositoryApplication extends MongoRepository<User, String> {

}
