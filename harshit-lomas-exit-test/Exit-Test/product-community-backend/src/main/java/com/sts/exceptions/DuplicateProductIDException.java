package com.sts.exceptions;

public class DuplicateProductIDException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String message;
	
	public DuplicateProductIDException() {}
	
	public DuplicateProductIDException(String msg) {
		super(msg);
		this.message=msg;
	}
}
