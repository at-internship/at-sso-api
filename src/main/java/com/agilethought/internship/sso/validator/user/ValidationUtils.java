package com.agilethought.internship.sso.validator.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    public static boolean isValidateString(String str) {
        if(str == null || str.isEmpty() || str.trim().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        Pattern patternEmail = Pattern.compile(
                "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$"
        );
        Matcher matcherEmail = patternEmail.matcher(email);
        return matcherEmail.find();
    }

    public static boolean isValidatePassword(String password) {
        Pattern patternPassword = Pattern.compile(
                // At least one numerical character
                // At least one lowercase character
                // At least one uppercase character
                // At least 10 characters long
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{10,}$"
        );
        Matcher matcherPassword = patternPassword.matcher(password);
        return matcherPassword.find();
    }

    public static boolean isLowerCaseString(String str) {
        return str.equals(str.toLowerCase());
    }
}
