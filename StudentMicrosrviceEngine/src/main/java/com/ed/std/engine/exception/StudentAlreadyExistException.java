package com.ed.std.engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	public StudentAlreadyExistException(String message) {
		super(message);
	}

}
