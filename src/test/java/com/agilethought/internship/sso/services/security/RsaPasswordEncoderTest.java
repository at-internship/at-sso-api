package com.agilethought.internship.sso.services.security;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class RsaPasswordEncoderTest {
    @InjectMocks
    private RsaPasswordEncoder rsaPasswordEncoder;
   
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		String PUBLIC_KEY = System.getenv("PUBLIC_KEY");
		String PRIVATE_KEY = System.getenv("PRIVATE_KEY");
		rsaPasswordEncoder = new RsaPasswordEncoder(PUBLIC_KEY, PRIVATE_KEY);
	}

	@Test
	public void testEncryptAndDecrypt() {		
		String originalPassword = "AgileThought4";
		String passwordEncrypted = rsaPasswordEncoder.encode(originalPassword);
		String passwordDecrypted = rsaPasswordEncoder.decode(passwordEncrypted);
		assertEquals(originalPassword, passwordDecrypted);
	}
  
}