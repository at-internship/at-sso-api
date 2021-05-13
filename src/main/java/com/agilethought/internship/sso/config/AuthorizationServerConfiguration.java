package com.agilethought.internship.sso.config;

import com.agilethought.internship.sso.services.security.TokenStoreService;
import com.agilethought.internship.sso.services.security.CustomTokenAccessEnhancer;
import com.agilethought.internship.sso.services.security.CustomUserDetailsService;
import com.agilethought.internship.sso.services.security.UserAuthProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserAuthProviderService userAuthProviderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenStoreService tokenStoreService;

    @Value("${sso.client.id}")
    private String clientId;

    @Value("${sso.client.secret}")
    private String clientSecret;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .authorizedGrantTypes("password", "client_credentials")
                .scopes("all")
                .accessTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
//                .pathMapping("/oauth/token", "/api/v1/login")
                //.pathMapping("oauth/check_token", "/api/v1/tokens") Ale
                .tokenStore(tokenStoreService)
                .authenticationManager(userAuthProviderService)
                .userDetailsService(userDetailsService)
                .tokenEnhancer(getCustomTokenAccessTokenEnhancer());
    }

    @Bean
    public TokenEnhancer getCustomTokenAccessTokenEnhancer() {
        return new CustomTokenAccessEnhancer();
    }
}
