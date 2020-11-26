package com.agilethought.internship.sso.jacoco;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.agilethought.internship.sso.model.Palindrome;

public class PalindromeTest {
	Palindrome palindromeTester = new Palindrome();
	
	@Test
	public void whenPalindrom_thenAccept() {	    
	    assertTrue(palindromeTester.isPalindrome("noon"));
	}
	
	@Test
	public void whenNearPalindrom_thanReject() {
	    assertFalse(palindromeTester.isPalindrome("neon"));
	}	
	 
}//End class
