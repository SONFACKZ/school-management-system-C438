package com.sms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidProcessingState extends RuntimeException {
	private String message;
	
	public InvalidProcessingState(String message) {
		super(message);
	}
}
