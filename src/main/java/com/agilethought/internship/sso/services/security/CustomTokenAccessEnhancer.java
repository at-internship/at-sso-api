package com.agilethought.internship.sso.services.security;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Component
public class CustomTokenAccessEnhancer implements TokenEnhancer {

    @Autowired
    public RepositoryApplication repositoryApplication;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        User user = repositoryApplication.findByEmail(authentication.getName());
        if (nonNull(user)) {
            additionalInfo.put("_id", user.getId());
        }
        ((DefaultOAuth2AccessToken) oAuth2AccessToken)
                .setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
