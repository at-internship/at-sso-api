package com.agilethought.internship.sso.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethougth.intership.sso.errorhandling.HttpExceptionMessage;
import com.agilethougth.intership.sso.errorhandling.PathErrorMessage;
import sun.security.util.Password;

public class BusinessValidations{

	@Autowired
	ServiceApplication serviceApplication;

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
        if (!email.equals("")) {
        		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        		Matcher mather = pattern.matcher(email);

        		if (mather.find() == true)
        			response = false;
        		else
        			throw new BadRequestException(HttpExceptionMessage.BadRequestFormatMail,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);
        }
        else
        	{
        	response=true;
        	throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyMail,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);
        	}
    return response;
}

public static boolean EmptyPassword(String password) {
    boolean response= true ;
        if (!password.equals("")) response = false;
        else throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyPassword,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);
    return response;
}

public static boolean FilterCharacters(String password) {
    boolean response = true;
        if (password.contains(" ") || password.contains("\n"))
        throw new BadRequestException(HttpExceptionMessage.FilterCharacters, PathErrorMessage.pathApi, HttpStatus.BAD_REQUEST);
        return response;
    }


public static boolean InvalidStatus(Integer status) {
    boolean response= true ;
        if (status.equals(0) || status.equals(1)) response = false; 
        else throw new BadRequestException(HttpExceptionMessage.BadRequestInvalidStatus,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);	
    return response;
}

}//End class
