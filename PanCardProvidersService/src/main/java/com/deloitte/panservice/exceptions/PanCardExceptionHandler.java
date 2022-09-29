package com.deloitte.panservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PanCardExceptionHandler {

	@ExceptionHandler(value=PanCardNotFoundException.class)
	public ResponseEntity<Object> exception(PanCardNotFoundException ex){
		return new ResponseEntity<Object>("Pan Card Doesn't Exist", HttpStatus.NOT_FOUND);
	}
}
