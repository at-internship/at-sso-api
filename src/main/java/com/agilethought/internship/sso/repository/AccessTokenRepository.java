package com.agilethought.internship.sso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.agilethought.internship.sso.model.OAuth2AuthenticationAccessToken;

@Repository
public interface AccessTokenRepository extends MongoRepository<OAuth2AuthenticationAccessToken, String> {

	OAuth2AuthenticationAccessToken findById();

	@Query("{'tokenId' : ?0}")
	OAuth2AuthenticationAccessToken findByTokenId(String token);

	boolean existsByTokenId(String token);
	
}