package com.agilethought.internship.sso.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {
	public static PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	public static PrivateKey getPrivateKey(String base64PrivateKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	public static String encrypt(String data, String publicKey) {
		Cipher cipher;
		byte [] encyryptedBytes;
		String encryptedStr = "";
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
			encyryptedBytes = cipher.doFinal(data.getBytes());
			encryptedStr = Base64.getEncoder().encodeToString(encyryptedBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedStr;
	}

	
	public static String decrypt(String encryptedStr, String base64PrivateKey)  {
		Cipher cipher;
		byte [] encryptedBytes; 
		byte [] decryptedBytes = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(base64PrivateKey));
			System.out.println("Entre");
			encryptedBytes = Base64.getDecoder().decode(encryptedStr.getBytes());
			System.out.println("Entre 2");
			decryptedBytes = cipher.doFinal(encryptedBytes);
			System.out.println("Entre 3");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("70: " + e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			System.out.println("73: " + e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			System.out.println("76: " + e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			System.out.println("79: " + e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			System.out.println("82: " + e);
		}

		return new String(decryptedBytes);
	}
}
