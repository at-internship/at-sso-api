package com.agilethought.internship.sso.services.security;

import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.INVALID_CREDENTIALS;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.agilethought.internship.sso.exception.AuthenticationException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UserAuthProviderService implements AuthenticationManager {

    @Autowired
    private RepositoryApplication repositoryApplication;

    @Autowired
    private RsaPasswordEncoder rsaPasswordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    private Authentication signInUser(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, user.getId(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    	String email = auth.getName();
        String password = auth.getCredentials().toString();
        password = rsaPasswordEncoder.decode(password);
        User user = repositoryApplication.findByEmail(email);
        if (user != null && rsaPasswordEncoder.matches(password, user.getPassword()))
            return signInUser(user);
        else
            throw new AuthenticationException(INVALID_CREDENTIALS);
    }
}
