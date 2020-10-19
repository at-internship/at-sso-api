package com.agilethought.internship.sso.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;

import com.agilethought.internship.sso.ATSSOApplication;
import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethougth.intership.sso.errorhandling.HttpExceptionMessage;
import com.agilethougth.intership.sso.errorhandling.PathErrorMessage;
import com.mongodb.client.MongoClients;
import com.agilethought.internship.sso.model.User;

public class BusinessMethods {

public static boolean EmptyName(String namefield) {
    boolean response= true ;
        if (!namefield.equals("")) response = false; 
        else throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyName,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);	
    return response;
}

public static boolean EmptyFirstName(String firstname) {
    boolean response= true ;
        if (!firstname.equals("")) response = false; 
        else throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyFirstName,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);	
    return response;
}

public static boolean WrongEmail(String email) {
    boolean response= true ;
    String e="";
        if (!email.equals("")) {
        	e=email.toLowerCase().trim(); 
        		Pattern pattern = Pattern
        				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        		Matcher mather = pattern.matcher(e);

        		if (mather.find() == true) {
        			response = false; 
        			String ex=e.substring(e.length() - 3);
        			ATSSOApplication.logger.info(ex);
        			if(!ex.equals("com")&&!ex.equals(".es")) {
        				throw new BadRequestException(HttpExceptionMessage.BadRequestFormatMail,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);        				
        			}
        		} else {
        			response=true;
        			throw new BadRequestException(HttpExceptionMessage.BadRequestFormatMail,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);
        		}        		
        	}        
        else
        	{
        	response=true;
        	throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyMail,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);	
        	}
    return response;
}

public static boolean ExistingEmail(User user) {
	
    boolean response= true ;
    MongoOperations mongo = new MongoTemplate(MongoClients.create(), "database");
	Query query = new Query();
	query.addCriteria(Criteria.where("email").is(user.getEmail()));
	List<User> users = mongo.find(query, User.class);
	
        if (users.isEmpty()) response = false; 
        else throw new BadRequestException(HttpExceptionMessage.BadRequestMailAlreadyExists,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);	
    return response;
}

public static boolean EmptyPassword(String password) {
    boolean response= true ;
        if (!password.equals("")) response = false; 
        else throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyPassword,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);	
    return response;
}

/*public static boolean EmptyStatus(Integer status) {  ////////???????????????????????????????????????
    boolean response= true ;
    String a=String.valueOf(status);
    try {    	
        if (a.equals(null)) 
        	response = false; 
    }
    catch(HttpMessageNotReadableException e){
    	throw new HttpMessageNotReadableException(HttpExceptionMessage.BadRequestEmptyStatus,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);
    }
    return response;
}*/

public static boolean InvalidStatus(Integer status) {
    boolean response= true ;
        if (status.equals(0) || status.equals(1)) response = false; 
        else throw new BadRequestException(HttpExceptionMessage.BadRequestInvalidStatus,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);	
    return response;
}
 
 
}//End class