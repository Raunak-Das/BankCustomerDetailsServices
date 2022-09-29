package com.deloitte.bankservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankCustomerDetailsServiceExceptionHandler {

	@ExceptionHandler(value=AadharCardProvidersServiceDownException.class)
	public ResponseEntity<Object> exception(AadharCardProvidersServiceDownException ex){
		return new ResponseEntity<Object>("KYC Failed because Aadhar Card Providers Service is Down",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AadharCardProvidersServiceDownException2.class)
	public ResponseEntity<Object> exception(AadharCardProvidersServiceDownException2 ex){
		return new ResponseEntity<Object>("Customer Added Successfully but KYC Failed because Aadhar Card Providers Service is Down",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=PanCardProvidersServiceDownException.class)
	public ResponseEntity<Object> exception(PanCardProvidersServiceDownException ex){
		return new ResponseEntity<Object>("Pan Card Validation Failed because Pan Card Providers Service is Down", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=PanCardProvidersServiceDownException2.class)
	public ResponseEntity<Object> exception(PanCardProvidersServiceDownException2 ex){
		return new ResponseEntity<Object>("Customer Added Successfully but Pan Card Validation Failed because Pan Card Providers Service is Down", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AccountNotFoundException.class)
	public ResponseEntity<Object> exception(AccountNotFoundException ex){
		return new ResponseEntity<Object>("Account No. Doesn't Exist", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=KYCFailedException.class)
	public ResponseEntity<Object> exception(KYCFailedException ex){
		return new ResponseEntity<Object>("Customer Added Successfully but KYC Failed because Aadhar No. Doesn't Exist", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=PanValidationFailedException.class)
	public ResponseEntity<Object> exception(PanValidationFailedException ex){
		return new ResponseEntity<Object>("Customer Added Successfully but Pan Card Validation Failed because Pan No. Doesn't Exist", HttpStatus.NOT_FOUND);
	}
}

