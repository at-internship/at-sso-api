package com.agilethought.internship.sso.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;

@Component
public class AlgorithmTest implements CommandLineRunner{
	@Value("${PUBLIC_KEY}")
	private String PUBLIC_KEY;
	@Value("${PRIVATE_KEY}")	
	private String PRIVATE_KEY;
	
	@Override
	public void run(String... args) throws Exception{
			String originalString = "abajoRSA2kk";
			System.out.println("originalString: " + originalString);
			
			String encryptedString = RSAUtil.encrypt(originalString, PUBLIC_KEY);
			System.out.println("EncryptedString: " + encryptedString);
			//String encryptedString = "$2a$10$0uUlCUH.zxl4sQoh6kd2hObKYJoAA/CpbBlE1hm0V23NrxvnRLLPi";
			String decryptedString = RSAUtil.decrypt(encryptedString, PRIVATE_KEY);
			System.out.println("DecryptedString: " + decryptedString);
			
			System.out.println("\n==========================================\n");
			
			// ORIGNAL
			// String encryptedStringFromJS = "e92M/K8sWnlSfY1z/cg/XnBKxQ7IJ4jT0ClmA7tcVYKxS28pk5KQ0R8PwTkIQXis9vXFU6KT6Mmj2AEISObmUdYfkBOwPL2XNToRQszwz8mUHd09ckAkameiAUhLToftBxd3zH/4r1UXdtlBg/gTTBqhwqar9l0fprN5+LXH+sFWUS6GNXGqjGNQcDRr8bPfCCkAyyxUMLw4wV+Evtjixrxj0mQNLoFSEr15W3bk7/lBxgcY6KICoT8hgutiJOpTWO5YuRLUyKMeouTwEvHAC9RXuy12fmBxhL5umuLujAbNLKZmDosen8AxDPIhZqqX/vsQJmUx6vKbIP4F1TuWVw==";
			// FE Team: GoF
			String encryptedStringFromJS = "CRrdgJyzkJO4AR9kERnYnczv+i8jJI5acQ2kbZF1KWIxaCUvdcAmtEQP0oaXlouS5eJXNR2g1c13FuykNOkDO1ZIIvxXfaMbQFBDXKGakJWb4T0lDX78Ji/Etap/B3+QayOwCxkVeTLxyF2/CrglT7gorcoH/9tTf/kPWXrUyVigr4OSJhFWPTrx62aHZKYEFuwca/OdIiuoFKbineCldSEd8ZF+ZU+rhE2I547mG91cleBt6bpwgkAjY4j1l7ZcxrbbXkfwNKLwSTvQzXJK+tRmJrMuUD46BvHmO2Ifll/MBbfWwpVLo5uYfG3eWwPXDTuhchWSmnKjbMImdG0luw==";
			//String encryptedStringFromJS = "$2a$10$4yB96O2GKeJmsePLOoGV6uaNFedOp7W4ay19GYTGa6rHRXxK7L9mC";//This encoding isn't Base64
			System.out.println("encryptedString From JS: " + encryptedStringFromJS);
			
			String decryptedStringFromJS = RSAUtil.decrypt(encryptedStringFromJS, PRIVATE_KEY);
			System.out.println("DecryptedString From JS: " + decryptedStringFromJS);
			
		
	}

}
