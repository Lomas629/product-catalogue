package com.sts.exceptions;

@SuppressWarnings("serial")
public class EmailAlreadyExist extends RuntimeException {
	
	@SuppressWarnings("unused")
	private String message;
	public EmailAlreadyExist() {}
	
	public EmailAlreadyExist(String msg) {
		super(msg);
		this.message=msg;
	}
	
	

}
