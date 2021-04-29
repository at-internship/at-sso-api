package com.agilethought.internship.sso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import static com.agilethought.internship.sso.exception.GlobalExceptionBody.ErrorDetails;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private List<ErrorDetails> errorDetails;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, List<ErrorDetails> details) {
		super(message);
		this.errorDetails = details;
	}
	
}
