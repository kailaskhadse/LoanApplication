package com.loanapplication.exceptions;

public class CustomerAlreadyRegisteredException extends RuntimeException {

	public CustomerAlreadyRegisteredException(String message) {
		super(message);
	}

}
