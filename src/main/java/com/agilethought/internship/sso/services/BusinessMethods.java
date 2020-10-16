package com.agilethought.internship.sso.services;



import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.agilethougth.intership.sso.errorhandling.HttpExceptionMessage;
import com.agilethougth.intership.sso.errorhandling.TypeError;

public class BusinessMethods {
	
	
 public static boolean notEmptyName(String namefield) {		
		
		//Name Field
		
boolean flag = true;
		

	if (namefield.isEmpty())
		flag = false;
	
	else {
	flag = true;
	
	throw new ResponseStatusException(HttpStatus.CONFLICT, HttpExceptionMessage.MandatoryName400 );
	

	}
		return flag ;
		}
 
 
 
 public static String capitalizeWord(String str){
     String words[]=str.split("\\s");
     String capitalizeWord="";
     for(String w:words){
         String first=w.substring(0,1);
         String afterfirst=w.substring(1);
         capitalizeWord+=first.toUpperCase()+afterfirst+" ";
     }
     return capitalizeWord.trim();
 }
 }
		
		
		
		
		//ID Field
		
		//FirstName Field
		
		//Password Field
		
		
		
		//Status Field
		
//		public static boolean onlyTwoValues(int statusvalue) {
//			
//		if (statusvalue == 0 || statusvalue == 1) {
//			return true;
//		}
//		return false;
//		}