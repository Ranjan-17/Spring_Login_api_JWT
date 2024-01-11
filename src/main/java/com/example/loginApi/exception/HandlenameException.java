package com.example.loginApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class HandlenameException {
	
	@ExceptionHandler(NameAlreadyExistException.class)
	public ResponseEntity<Object> handleNameexistException(NameAlreadyExistException e){
		NameOrEmailAlreadyException name =new NameOrEmailAlreadyException(
				e.getMessage(),HttpStatus.ALREADY_REPORTED);
		
		return new  ResponseEntity<>(name,HttpStatus.ALREADY_REPORTED);
		
	}	
		
		@ExceptionHandler(EmailAlreadyExistException.class)
		public ResponseEntity<?> handleEmailexistException(EmailAlreadyExistException e){
			NameOrEmailAlreadyException name =new NameOrEmailAlreadyException(
					e.getMessage(),HttpStatus.ALREADY_REPORTED);
			
			return new  ResponseEntity<>(name,HttpStatus.ALREADY_REPORTED);
			
	}

}
