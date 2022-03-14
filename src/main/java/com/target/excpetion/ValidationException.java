package com.target.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST) 
public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = -720699798265005105L;

	public ValidationException(String error) {
		super(error);
	}

}
