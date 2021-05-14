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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.agilethought.internship.sso.utils.RSAUtil;
import org.springframework.stereotype.Component;

@Component
public class UserAuthProviderService implements AuthenticationManager {

    @Autowired
    private RepositoryApplication repositoryApplication;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Value("${PRIVATE_KEY}")
    private String PRIVATE_KEY;

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
        // RSA decrypt
        String password = auth.getCredentials().toString();
        System.out.println("THE PASSWORD START: " + password);
		password = RSAUtil.decrypt(password, PRIVATE_KEY);
		
		System.out.println("THE PASSWORD END: " + password);
		
        User user = repositoryApplication.findByEmail(email);
        //System.out.println("user.getPassword: " + RSAUtil.decrypt(user.getPassword(), PRIVATE_KEY));
        System.out.println("password: " + password);
        System.out.println("matches: " + passwordEncoder.matches(password, user.getPassword()));
        if (user != null && passwordEncoder.matches(password, RSAUtil.decrypt(user.getPassword(), PRIVATE_KEY)))
        //if (user != null && user.getPassword().equals(password))
            return signInUser(user);
        else
            throw new AuthenticationException(INVALID_CREDENTIALS);
    }
}
