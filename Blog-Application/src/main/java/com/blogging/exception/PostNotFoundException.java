package com.blogging.exception;

public class PostNotFoundException extends Exception {

	public PostNotFoundException() {
		
	}
	
	public PostNotFoundException(String message) {
		super(message);
	}
}
