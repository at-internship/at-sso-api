package com.agilethought.internship.sso.controller;

import java.util.Date;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.agilethought.internship.sso.exception.ExceptionResponse;
import com.agilethought.internship.sso.model.ApiError;
import com.agilethought.internship.sso.exception.errrohandling.HttpExceptionMessage;
import com.agilethought.internship.sso.exception.BadRequestException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = request.getDescription(false).substring(4);
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, 400, HttpExceptionMessage.BAD_REQUEST_JSON, path));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
	}

	@ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException e) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getStatus(), HttpStatus.BAD_REQUEST,
				e.getMessage(), e.getPath());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}// End class