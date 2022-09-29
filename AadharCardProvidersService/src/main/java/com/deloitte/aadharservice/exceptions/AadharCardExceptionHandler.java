package com.deloitte.aadharservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AadharCardExceptionHandler {

	@ExceptionHandler(value=AadharCardNotFoundException.class)
	public ResponseEntity<Object> exception(AadharCardNotFoundException ex){
		return new ResponseEntity<Object>("Aadhar Card Doesn't Exist", HttpStatus.NOT_FOUND);
	}
}
