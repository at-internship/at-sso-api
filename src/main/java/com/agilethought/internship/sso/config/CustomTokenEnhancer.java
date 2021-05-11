package com.agilethought.internship.sso.config;

import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.INVALID_CREDENTIALS;

import com.agilethought.internship.sso.exception.AuthenticationException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Autowired
    private RepositoryApplication repositoryApplication;

    @Override
    public OAuth2AccessToken enhance(
            OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
        User userFromDb = repositoryApplication.findByEmail(authentication.getName());
        if (userFromDb == null) throw new AuthenticationException(INVALID_CREDENTIALS);
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("user_id", userFromDb.getId());
        ( (DefaultOAuth2AccessToken)accessToken ).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
