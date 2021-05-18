package com.agilethought.internship.sso.repository;

import com.agilethought.internship.sso.model.OAuth2AuthenticationAccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface OAuth2AccessTokenRepository extends
        MongoRepository<OAuth2AuthenticationAccessToken, Serializable> {

    @Query(value = "{'authenticationId': ?0}")
    boolean existsByAuthenticationId(String authId);

    boolean existsByTokenId(String tokenId);

    void deleteByTokenId(String tokenId);

	@Query("{'tokenId' : ?0}")
	OAuth2AuthenticationAccessToken findByTokenId(String token);
}
