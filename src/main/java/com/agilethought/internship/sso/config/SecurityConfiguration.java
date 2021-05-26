package com.agilethought.internship.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile(value="!test")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String TOKEN_CREATION_ENDPOINT = "/api/v1/login";

	@Value("${sso.client.id}")
	   private String clientId;
	
	@Value("${sso.client.secret}")
	   private String clientSecret;
	
	 @Autowired
	   private PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf()
				.disable()
				.authorizeRequests()
				.antMatchers(TOKEN_CREATION_ENDPOINT).permitAll()
				.antMatchers("/api/v1/users/**").authenticated()
		        .antMatchers("/api/v1/tokens/**").authenticated()
		        .and()
		        .httpBasic();
	}
	
	@Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.inMemoryAuthentication().withUser(clientId).password(passwordEncoder.encode(clientSecret)).roles("ADMIN");
	   }

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
