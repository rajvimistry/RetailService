package com.target.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND) 
public class RecordNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1852769088230088753L;

	public RecordNotFoundException(String error) {
		super(error);
	}

}
