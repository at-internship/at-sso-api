package com.agilethought.internship.sso.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.agilethought.internship.sso.services.security.RsaPasswordEncoder;

import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;

@Component
public class AlgorithmTest implements CommandLineRunner{
	@Value("${sso.enc.key.public}")
	private String PUBLIC_KEY;
	@Value("${sso.enc.key.private}")	
	private String PRIVATE_KEY;
	
	@Autowired
	private RsaPasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception{
			String originalString = "IamAnAutomatedCOP21";
			System.out.println("originalString: " + originalString);
			
			String encryptedString = passwordEncoder.encode(originalString);
			System.out.println("EncryptedString: " + encryptedString);
			String decryptedString = passwordEncoder.decode(encryptedString);
			System.out.println("DecryptedString: " + decryptedString);
			
			System.out.println("\n==========================================\n");
	}

}
