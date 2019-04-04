package com.khoubyari.example.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class SPFException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3772367625601295465L;

	/**
	 * Error message
	 */
	@Getter
	private final String message;

	/**
	 * Http Status errorcode
	 */
	@Getter
	private final HttpStatus httpStatus;

	public SPFException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

}
