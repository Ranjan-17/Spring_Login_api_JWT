package com.example.loginApi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class NameOrEmailAlreadyException {
	
	@Autowired
	private String message;
	
	@Autowired
	private HttpStatus httpStatus;
	
	
	public  NameOrEmailAlreadyException(String message,HttpStatus httpStatus) {
		this.message=message;
		
		this.httpStatus=httpStatus;
	}
	
	   public String getMessage() {
		   return message;
		   
	   }
	   
	   public HttpStatus getHttpStatus() {
		   return httpStatus;
	   }

}


