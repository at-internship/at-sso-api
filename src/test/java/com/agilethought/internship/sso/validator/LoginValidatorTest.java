package com.agilethought.internship.sso.validator;

import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.exception.BadRequestExceptionNew;
import com.agilethought.internship.sso.exception.UnauthorizedException;
import com.agilethought.internship.sso.validator.user.LoginValidator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LoginValidatorTest {

    @Test
    public void validateEmailTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("juan.perez@agilethought.com");
        loginRequest.setPassword("HelloWorld123");

        Assertions.assertAll(
                () -> {
                    loginRequest.setEmail(null);
                    // check if the email is null
                    Exception exception = Assertions.assertThrows(BadRequestExceptionNew.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "One or more fields are invalid",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("");
                    Exception exception = Assertions.assertThrows(BadRequestExceptionNew.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "One or more fields are invalid",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("  ");
                    Exception exception = Assertions.assertThrows(BadRequestExceptionNew.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("One or more fields are invalid",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("juanperez.com");
                    Exception exception = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("juan@example");
                    Exception exception = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            exception.getMessage()
                    );
                }
        );
    }

    @Test
    public void validatePasswordTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("owen@example.com");
        loginRequest.setPassword("Holamundo123");

        Assertions.assertAll(
                () -> {
                    loginRequest.setPassword(null);
                    Exception errorMessagePassword = Assertions.assertThrows(BadRequestExceptionNew.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("One or more fields are invalid", errorMessagePassword.getMessage());
                },
                () -> {
                    loginRequest.setPassword("");
                    Exception errorMessagePassword = Assertions.assertThrows(BadRequestExceptionNew.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("One or more fields are invalid", errorMessagePassword.getMessage());
                },
                () -> {
                    loginRequest.setPassword("  ");
                    Exception errorMessagePassword = Assertions.assertThrows(BadRequestExceptionNew.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("One or more fields are invalid", errorMessagePassword.getMessage());
                },
                () -> {
                    loginRequest.setPassword("hiWorld");
                    Exception errorMessagePassword = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            errorMessagePassword.getMessage()
                    );
                },
                () -> {
                    loginRequest.setPassword("helloworld");
                    Exception errorMessagePassword = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator  loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            errorMessagePassword.getMessage()
                    );
                },
                () -> {
                    loginRequest.setPassword("helloWorld");
                    Exception errorMessagePassword = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator  loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            errorMessagePassword.getMessage()
                    );
                },
                ()-> {
                    loginRequest.setPassword("Helloworld1234");
                    LoginValidator  loginValidator = new LoginValidator();
                    loginValidator.validate(loginRequest);
                }
        );
    }

}
