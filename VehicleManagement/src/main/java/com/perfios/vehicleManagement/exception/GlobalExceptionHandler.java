package com.perfios.vehicleManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice // handle exception globally
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest,
			HttpStatus status) {
//	    	HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorDetails errorDetails = new ErrorDetails(new Date(), status, exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	// global handler to all other kind
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception, WebRequest webRequest, HttpStatus status) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), status, exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
