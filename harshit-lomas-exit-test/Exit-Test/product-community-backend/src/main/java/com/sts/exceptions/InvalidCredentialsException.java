package com.sts.exceptions;

public class InvalidCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String message;
	
	public InvalidCredentialsException() {}
	
	public InvalidCredentialsException(String msg) {
		super(msg);
		this.message=msg;
	}

}
