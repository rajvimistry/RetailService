package com.target.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR) 
public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -7104953853210993418L;

	public ServiceException(String error) {
		super(error);
	}

}
