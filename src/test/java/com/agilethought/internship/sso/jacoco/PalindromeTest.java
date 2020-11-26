package com.agilethought.internship.sso.jacoco;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
