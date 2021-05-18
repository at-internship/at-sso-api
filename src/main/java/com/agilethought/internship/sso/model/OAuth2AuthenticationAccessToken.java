package com.agilethought.internship.sso.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import lombok.Data;

@Document(collection = "oauth2_access_token")
@Data
public class OAuth2AuthenticationAccessToken implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String tokenId;
	private OAuth2AccessToken oAuth2AccessToken;
	private String authenticationId;
	private String userName;
	private String clientId;
	private OAuth2Authentication authentication;

	public OAuth2AuthenticationAccessToken(final OAuth2AccessToken oAuth2AccessToken,
			final OAuth2Authentication authentication, final String authenticationId) {
		this.tokenId = oAuth2AccessToken.getValue();
		this.oAuth2AccessToken = oAuth2AccessToken;
		this.authenticationId = authenticationId;
		this.userName = authentication.getName();
		this.clientId = authentication.getOAuth2Request().getClientId();
		this.authentication = authentication;
	}
}