package com.agilethought.internship.sso.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	
	private Date timestamp;
	private int status;
	private HttpStatus error;
	private String message;
	private String path;

}