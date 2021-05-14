package com.agilethought.internship.sso.services.security;

import static java.util.Objects.*;
import static org.apache.commons.lang3.StringUtils.*;

import com.agilethought.internship.sso.model.OAuth2AuthenticationAccessToken;
import com.agilethought.internship.sso.repository.OAuth2AccessTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class TokenStoreService implements TokenStore{

    @Autowired
    OAuth2AccessTokenRepository accessTokenRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    public OAuth2Authentication readAuthentication(String tokenId) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("tokenId").is(tokenId))
                .fields().exclude("authentication.userAuthentication");
        OAuth2AuthenticationAccessToken accessToken = mongoTemplate.findOne(
                query,
                OAuth2AuthenticationAccessToken.class,
                "oauth2_access_token");
        return accessToken == null ? null : accessToken.getAuthentication();
    }

    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        OAuth2AuthenticationAccessToken accessToken = new OAuth2AuthenticationAccessToken(
                token,
                authentication,
                authenticationKeyGenerator.extractKey(authentication));
        String tokenId = token.getValue();
        if (isNotEmpty(tokenId) && !accessTokenRepository.existsByTokenId(tokenId))
            mongoTemplate.save(accessToken);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenId)
            throws InvalidTokenException {
        Query query = new Query();
        query.addCriteria(Criteria.where("tokenId").is(tokenId))
                .fields().exclude("authentication");
        OAuth2AuthenticationAccessToken token = mongoTemplate.findOne(
                query,
                OAuth2AuthenticationAccessToken.class,
                "oauth2_access_token");
        if (isNull(token))
            throw new InvalidTokenException("Invalid token");
        return token.getOAuth2AccessToken();
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken accessToken) {
        String tokenId = accessToken.getValue();
        if (isNotEmpty(tokenId) && accessTokenRepository.existsByTokenId(tokenId))
            accessTokenRepository.deleteByTokenId(tokenId);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication oAuth2Authentication) {

    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String s) {
        return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        return null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {

    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);
        if (isEmpty(authenticationId)) {
            return null;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("authenticationId").is(authenticationId)).fields().exclude("authentication");
        OAuth2AuthenticationAccessToken token = mongoTemplate.findOne(query, OAuth2AuthenticationAccessToken.class, "oauth2_access_token");
        return token == null ? null : token.getOAuth2AccessToken();
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String s, String s1) {
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String s) {
        return null;
    }
}
