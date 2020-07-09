package com.sms.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFound.class)
	public final ResponseEntity<SMSException> handleResourceNotFound(Exception ex, WebRequest request) {
		
		SMSException excpt = new SMSException();
		excpt.setDate(new Date());
		excpt.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(excpt, HttpStatus.NOT_FOUND);
	}

}
