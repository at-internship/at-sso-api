package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.model.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    private User user = new User();

    @Test
    public void itShouldThrowErrorMessagesInTypeField() {
        this.user.setFirstName("Owen");
        this.user.setLastName("Ramirez");
        this.user.setEmail("owen@example.com");
        this.user.setPassword("HelloWorld123");
        this.user.setStatus(0);
        assertAll(
            () -> {
                // Check the message when the type field is null
                this.user.setType(null);
                Exception errorMessageException = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Type is missing.",
                    errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is 0
                this.user.setType(0);
                Exception errorMessageException = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Type. Correct format is: Number 1 for admin or number 2 for normal user",
                    errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is -1
                this.user.setType(-1);
                Exception errorMessageException = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Type. Correct format is: Number 1 for admin or number 2 for normal user",
                    errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is 3
                this.user.setType(3);
                Exception errorMessageException = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Type. Correct format is: Number 1 for admin or number 2 for normal user",
                    errorMessageException.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInFirstNameField() {
        this.user.setType(1);
        this.user.setLastName("Ramirez");
        this.user.setEmail("owen@example.com");
        this.user.setPassword("HelloWorld123");
        this.user.setStatus(0);
        assertAll(
            () -> {
                this.user.setFirstName(null);
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field First name is missing.",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setFirstName("");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field First name is missing.",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setFirstName("   ");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field First name is missing.",
                    exception.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInLastNameField() {
        this.user.setType(2);
        this.user.setFirstName("Owen");
        this.user.setEmail("owen@example.com");
        this.user.setPassword("HelloWorld123");
        this.user.setStatus(0);
        assertAll(
            () -> {
                this.user.setLastName(null);
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Last name is missing.",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setLastName("");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Last name is missing.",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setLastName("   ");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Last name is missing.",
                    exception.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInEmailField() {
        this.user.setType(1);
        this.user.setFirstName("Owen");
        this.user.setLastName("Ramirez");
        this.user.setPassword("HelloWorld123");
        this.user.setStatus(0);
        assertAll(
            () -> {
                this.user.setEmail(null);
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Email is missing.",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setEmail("");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Email is missing.",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setEmail("     ");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Email is missing.",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setEmail("owenexample.com");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Email. Correct format is: an_accepted-email.example@domain.com.mx",
                    exception.getMessage()
                );
            },
            () -> {
                this.user.setEmail("owen@example");
                Exception exception = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Email. Correct format is: an_accepted-email.example@domain.com.mx",
                    exception.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesPasswordField() {
        this.user.setType(2);
        this.user.setFirstName("Owen");
        this.user.setLastName("Ramirez");
        this.user.setEmail("owen@example.com");
        this.user.setStatus(0);
        assertAll(
            () -> {
                this.user.setPassword(null);
                Exception errorMessagePassword = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Password is missing.",
                    errorMessagePassword.getMessage()
                );
            },
            () -> {
                this.user.setPassword("");
                Exception errorMessagePassword = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Password is missing.",
                    errorMessagePassword.getMessage()
                );
            },
            () -> {
                this.user.setPassword("  ");
                Exception errorMessagePassword = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Password is missing.",
                    errorMessagePassword.getMessage()
                );
            },
            () -> {
                this.user.setPassword("hiWorld");
                Exception errorMessagePassword = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Password. Correct format is: 10 characters minimum with "
                            + "at least one lowercase letter, one uppercase letter, and one number.",
                    errorMessagePassword.getMessage()
                );
            },
            () -> {
                this.user.setPassword("helloworld");
                Exception errorMessagePassword = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Password. Correct format is: 10 characters minimum with "
                            + "at least one lowercase letter, one uppercase letter, and one number.",
                    errorMessagePassword.getMessage()
                );
            },
            () -> {
                this.user.setPassword("helloWorld");
                Exception errorMessagePassword = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Password. Correct format is: 10 characters minimum with "
                            + "at least one lowercase letter, one uppercase letter, and one number.",
                    errorMessagePassword.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInStatusField() {
        this.user.setType(1);
        this.user.setFirstName("Owen");
        this.user.setLastName("Ramirez");
        this.user.setEmail("owen@example.com");
        this.user.setPassword("HelloWorld123");
        assertAll(
            () -> {
                this.user.setStatus(null);
                Exception errorMessageStatus = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Required field Status is missing.",
                    errorMessageStatus.getMessage()
                );
            },
            () -> {
                this.user.setStatus(-1);
                Exception errorMessageStatus = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Status. Correct format is: Number 0 for unavailable or "
                            + "number 1 for available",
                    errorMessageStatus.getMessage()
                );
            },
            () -> {
                this.user.setStatus(2);
                Exception errorMessageStatus = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Status. Correct format is: Number 0 for unavailable or "
                            + "number 1 for available",
                    errorMessageStatus.getMessage()
                );
            },
            () -> {
                this.user.setStatus(10);
                Exception errorMessageStatus = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Status. Correct format is: Number 0 for unavailable or "
                            + "number 1 for available",
                    errorMessageStatus.getMessage()
                );
            },
            () -> {
                this.user.setStatus(-10);
                Exception errorMessageStatus = assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                assertEquals(
                    "Invalid input on field Status. Correct format is: Number 0 for unavailable or "
                            + "number 1 for available",
                    errorMessageStatus.getMessage()
                );
            },
            () -> {
                this.user.setStatus(0);
                UserValidator userValidator = new UserValidator();
                userValidator.validate(user);
            }
        );
    }

}